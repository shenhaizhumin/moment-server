package cn.example.moment.service;

import cn.example.moment.pojo.MomentEntity;

import java.util.List;

public interface MomentService {
    List<MomentEntity> getMoments();

    Boolean insertMoment(MomentEntity momentEntity);

    MomentEntity getMomentById(Long id);
}
