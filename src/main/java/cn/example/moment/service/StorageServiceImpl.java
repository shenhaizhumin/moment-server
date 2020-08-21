package cn.example.moment.service;

import cn.example.moment.dao.StorageMapper;
import cn.example.moment.pojo.StorageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public List<StorageEntity> getAllStorage() {
        return storageMapper.getAll();
    }

    @Override
    public Long addStorageFile(StorageEntity storageEntity) {
        return storageMapper.addStorageFile(storageEntity);
    }

    @Override
    public Boolean bindImageToMoment(Long moment_id, Long storage_id) {
        return storageMapper.bindImageToMoment(moment_id, storage_id);
    }
}
