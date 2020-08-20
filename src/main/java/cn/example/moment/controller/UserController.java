package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.api.body.UserBody;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.TokenService;
import cn.example.moment.service.UserService;
import cn.example.moment.utils.Constants;
import cn.example.moment.utils.Logger;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.ConstantCallSite;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {
    private String url = "jdbc:postgresql://39.107.77.70:5432/testdb";
    private String username = "zengqi";
    private String password = "123456";
    private Connection connection = null;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public BaseResponse<UserEntity> session(@RequestBody UserBody userBody) {
        String username = userBody.getUsername();
        String password = userBody.getPassword();
        Logger.logger.error(("username=" + username + ",pwd:" + password));
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        UserEntity userEntity = userService.getUser(params);
        if (null == userEntity) {
            return new BaseResponse<UserEntity>(-200, "Incorrect username");
        }
        //生成token
        String token = tokenService.getToken(userEntity.getUid());
        userEntity.setAccess_token(token);
        redisTemplate.opsForValue().set(userEntity.getUid(), token, Constants.ExpireTime, TimeUnit.MILLISECONDS);
        return new BaseResponse<UserEntity>(userEntity);
    }

    @LoginRequired
    @RequestMapping("/allUser")
    public List<UserEntity> findAll() {
        List<UserEntity> users = userService.getAll();
        System.out.println(users);
        return users;
    }

    @LoginRequired
    @RequestMapping("/getUser/{id}")
    public UserEntity queryUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @LoginRequired
    @GetMapping("/userInfo")
    public BaseResponse<UserEntity> getCurrentUser(@CurrentUser UserEntity currentUser){
        return new BaseResponse<>(currentUser);
    }

    @LoginRequired
    @PutMapping("/update")
    public BaseResponse<UserEntity> update(@CurrentUser UserEntity currentUser, @RequestBody UserEntity userBody) {
        Logger.logger.info("currentUser:" + currentUser.getUsername());
        userBody.setId(currentUser.getId());
        userService.update(userBody);
        return new BaseResponse<UserEntity>(userService.getUserById(currentUser.getId()));
    }

    /**
     * 登出
     */
    @LoginRequired
    @RequestMapping("/logout")
    public BaseResponse<Object> logout(@CurrentUser UserEntity currentUser) {
        String uid = currentUser.getUid();
        try {
            redisTemplate.delete(uid);
        } catch (Exception e) {
            return new BaseResponse<Object>(-200, "操作失败：" + e.getMessage());
        }
        return new BaseResponse<Object>();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public BaseResponse<UserEntity> register(@RequestBody UserBody userBody) {
        String password = userBody.getPassword();
        if (null == password || password.isEmpty()) {
            return new BaseResponse<UserEntity>(-200, "必须填写密码!");
        }
        HashMap<String, String> params = new HashMap<>();
        String email = userBody.getEmail();
        params.put("email", email);
        if (null != userService.getUser(params)) {
            return new BaseResponse<UserEntity>(-200, "邮箱已存在!");
        }
        String mobile = userBody.getMobile();
        params.clear();
        params.put("mobile", mobile);
        if (null != userService.getUser(params)) {
            return new BaseResponse<UserEntity>(-200, "手机号已存在!");
        }
        String username = userBody.getUsername();
        params.clear();
        params.put("username", username);
        if (null != userService.getUser(params)) {
            return new BaseResponse<UserEntity>(-200, "用户名已存在!");
        }
        // 新增用户
        userBody.setEnable((short) 1);
//        userBody.setLatest_ip();
        userBody.setLatest_time(System.currentTimeMillis());
        userBody.setRegister_time(System.currentTimeMillis());
        userBody.setUid(UUID.randomUUID().toString());
        userService.insert(userBody);
        String token = tokenService.getToken(userBody.getUid());
        redisTemplate.opsForValue().set(userBody.getUid(), token, Constants.ExpireTime, TimeUnit.MILLISECONDS);
        UserEntity user = userService.getUser(params);
        user.setAccess_token(token);
        return new BaseResponse<UserEntity>(user);
    }
}
