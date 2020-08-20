package cn.example.moment.dao;

import cn.example.moment.pojo.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentEntity> getAll();

    List<CommentEntity> getComments(CommentEntity commentEntity);

    Boolean insertComment(CommentEntity commentEntity);
}
