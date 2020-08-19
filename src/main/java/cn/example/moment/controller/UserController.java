package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.api.body.UserBody;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.TokenService;
import cn.example.moment.service.UserService;
import cn.example.moment.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        UserEntity userEntity = userService.getUserByUsername(username);
        if (null == userEntity) {
            return new BaseResponse<UserEntity>(-200, "Incorrect username");
        }
        //生成token
        String token = tokenService.getToken(userEntity);
        userEntity.setAccess_token(token);
        redisTemplate.opsForValue().set(userEntity.getId() + "", token);
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
    @RequestMapping("/queryUser/{id}")
    public UserEntity queryUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @LoginRequired
    @PutMapping("/update")
    public BaseResponse<UserEntity> update(@CurrentUser UserEntity currentUser, @RequestBody UserEntity userBody) {
        Logger.logger.info("currentUser:" + currentUser.getUsername());
        userBody.setId(currentUser.getId());
        userService.update(userBody);
        return new BaseResponse<UserEntity>(userService.getUserById(currentUser.getId()));
    }
}
