package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.pojo.MomentEntity;
import cn.example.moment.pojo.StorageEntity;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.MomentService;
import cn.example.moment.service.StorageService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class MomentController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MomentService momentService;
    @Autowired
    private StorageService storageService;

    @LoginRequired
    @RequestMapping("/moments")
    public List<MomentEntity> getMoments() {
        return momentService.getMoments();
    }

    @LoginRequired
    @PostMapping("/insertMoment")
    public BaseResponse<Object> insertMoment(@CurrentUser UserEntity currentUser, @RequestBody MomentEntity momentEntity) {
        momentEntity.setPublish_time(new Timestamp(System.currentTimeMillis()));
        momentEntity.setUser_id(currentUser.getId());
        Boolean insertMoment = momentService.insertMoment(momentEntity);
        if (insertMoment == null || !insertMoment) {
            return new BaseResponse<>(-200, "发布动态失败！");
        }
        List<StorageEntity> storages = momentEntity.getStorages();
        if (storages != null && storages.size() > 0) {
            for (StorageEntity storage : storages) {
                Long id = storage.getId();
                if (id != null) {
                    storageService.bindImageToMoment(momentEntity.getId(), id);
                }
            }
        }
        logger.info("insert moment success:" + JSON.toJSONString(momentEntity));
        return new BaseResponse<>();
    }
}
