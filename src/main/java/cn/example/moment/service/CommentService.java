package cn.example.moment.service;

import cn.example.moment.pojo.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getAll();

    List<CommentEntity> getComments(CommentEntity commentEntity);

    Boolean insertComment(CommentEntity commentEntity);
}
