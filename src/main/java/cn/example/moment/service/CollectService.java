package cn.example.moment.service;

import cn.example.moment.pojo.CollectEntity;

import java.util.List;

public interface CollectService {
    List<CollectEntity> getAll();

    Boolean insertCollect(CollectEntity entity);

    CollectEntity getCollectByUserId(Long user_id, Long moment_id);
}
