package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.pojo.CollectEntity;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.CollectService;
import cn.example.moment.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;
    @Autowired
    private MomentService momentService;

//    @LoginRequired
    @GetMapping("/collects")
    public BaseResponse<List<CollectEntity>> getCollects() {
        return new BaseResponse<>(collectService.getAll());
    }

    @LoginRequired
    @PostMapping("/insertCollect")
    public BaseResponse<Object> insertCollect(@CurrentUser UserEntity currentUser, @RequestBody CollectEntity collectEntity) {
        if (null == momentService.getMomentById(collectEntity.getMoment_id())) {
            return new BaseResponse<>(-200, "moment not exists!");
        }
        //不能重复点赞 收藏
        Long moment_id = collectEntity.getMoment_id();
        if (collectService.getCollectByUserId(currentUser.getId(), moment_id) != null) {
            return new BaseResponse<>(-200, "already collected!");
        }
        collectEntity.setCreate_time(new Timestamp(System.currentTimeMillis()));
        collectEntity.setOperator_user_id(currentUser.getId());
        Boolean insertCollect = collectService.insertCollect(collectEntity);
        if (insertCollect != null && insertCollect) {
            return new BaseResponse<>();
        } else {
            return new BaseResponse<>(-200, "insert failed!");
        }

    }
}
