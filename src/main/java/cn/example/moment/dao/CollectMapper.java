package cn.example.moment.dao;

import cn.example.moment.pojo.CollectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper {
    List<CollectEntity> getAll();

    Boolean insertCollect(CollectEntity collectEntity);

    CollectEntity getCollectByUserId(@Param("user_id") Long user_id, @Param("moment_id") Long moment_id);
}
