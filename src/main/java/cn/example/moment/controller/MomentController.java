package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.pojo.MomentEntity;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class MomentController {
    @Autowired
    private MomentService momentService;

    @RequestMapping("/moments")
    public List<MomentEntity> getMoments() {
        return momentService.getMoments();
    }

    @LoginRequired
    @PostMapping("/insertMoment")
    public BaseResponse<Object> insertMoment(@CurrentUser UserEntity currentUser, @RequestBody MomentEntity momentEntity) {
        momentEntity.setPublish_time(new Timestamp(System.currentTimeMillis()));
        momentEntity.setUser_id(currentUser.getId());
        momentService.insertMoment(momentEntity);
        return new BaseResponse<>();
    }
}
