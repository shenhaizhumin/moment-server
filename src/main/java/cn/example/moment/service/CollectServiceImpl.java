package cn.example.moment.service;

import cn.example.moment.dao.CollectMapper;
import cn.example.moment.pojo.CollectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public List<CollectEntity> getAll() {
        return collectMapper.getAll();
    }

    @Override
    public Boolean insertCollect(CollectEntity entity) {
        return collectMapper.insertCollect(entity);
    }

    @Override
    public CollectEntity getCollectByUserId(Long user_id,Long moment_id) {
        return collectMapper.getCollectByUserId(user_id, moment_id);
    }
}
