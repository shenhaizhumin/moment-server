package cn.example.moment.dao;

import cn.example.moment.pojo.StorageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorageMapper {

    List<StorageEntity> getAll();

    Long addStorageFile(StorageEntity storageEntity);

    Boolean bindImageToMoment(@Param("moment_id") Long moment_id, @Param("storage_id") Long storage_id);
}
