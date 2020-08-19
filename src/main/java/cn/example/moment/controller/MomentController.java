package cn.example.moment.controller;

import cn.example.moment.pojo.MomentEntity;
import cn.example.moment.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MomentController {
    @Autowired
    private MomentService momentService;

    @RequestMapping("/moments")
    public List<MomentEntity> getMoments() {
        return momentService.getMoments();
    }
}
