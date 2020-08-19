package cn.example.moment.dao;

import cn.example.moment.pojo.MomentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MomentMapper {
    @Select("select * from moment")
    List<MomentEntity> findMoments();
}
