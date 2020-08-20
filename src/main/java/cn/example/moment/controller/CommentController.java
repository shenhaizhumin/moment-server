package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.pojo.CommentEntity;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.CommentService;
import cn.example.moment.service.MomentService;
import cn.example.moment.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    MomentService momentService;

    @GetMapping("/comments")
    public BaseResponse<List<CommentEntity>> getAll() {
        return new BaseResponse<>(commentService.getAll());
    }

    @LoginRequired
    @PostMapping("/insertComment")
    public BaseResponse<Object> insertComment(@CurrentUser UserEntity currentUser, @RequestBody CommentEntity commentEntity) {
        if (momentService.getMomentById(commentEntity.getMoment_id()) == null) {
            return new BaseResponse<>(-200, "回复的动态不存在或已被删除");
        }
        commentEntity.setOperator_user_id(currentUser.getId());
        commentEntity.setPublish_time(new Timestamp(System.currentTimeMillis()));
        Boolean insertComment = commentService.insertComment(commentEntity);
        Logger.logger.info("insertComment--------------:" + insertComment);
        if (null != insertComment && insertComment) {
            return new BaseResponse<>();
        } else {
            return new BaseResponse<>(-200, "评论失败！");
        }
    }

    /**
     * 根据moment id 查询所有评论
     *
     * @param moment_id
     * @param user_id
     * @return
     */
    @GetMapping("/getComments")
    public BaseResponse<List<CommentEntity>> getComments(@Param("moment_id") Long moment_id, @Param("user_id") Long user_id) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMoment_id(moment_id);
        commentEntity.setOperator_user_id(user_id);
        return new BaseResponse<>(commentService.getComments(commentEntity));
    }
}
