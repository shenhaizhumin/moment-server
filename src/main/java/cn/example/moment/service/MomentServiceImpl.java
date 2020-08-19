package cn.example.moment.service;

import cn.example.moment.dao.MomentMapper;
import cn.example.moment.pojo.MomentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    private MomentMapper momentMapper;

    @Override
    public List<MomentEntity> getMoments() {
        return momentMapper.findMoments();
    }
}
