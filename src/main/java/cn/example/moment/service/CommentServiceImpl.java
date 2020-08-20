package cn.example.moment.service;

import cn.example.moment.dao.CommentMapper;
import cn.example.moment.pojo.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentEntity> getAll() {
        return commentMapper.getAll();
    }

    @Override
    public List<CommentEntity> getComments(CommentEntity commentEntity) {
        return commentMapper.getComments(commentEntity);
    }

    @Override
    public Boolean insertComment(CommentEntity commentEntity) {
        return commentMapper.insertComment(commentEntity);
    }
}
