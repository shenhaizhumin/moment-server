package cn.example.moment.service;

import cn.example.moment.pojo.StorageEntity;

import java.util.List;

public interface StorageService {
    List<StorageEntity> getAllStorage();

    Long addStorageFile(StorageEntity storageEntity);

    Boolean bindImageToMoment(Long moment_id, Long storage_id);
}
