package cn.example.moment.dao;

import cn.example.moment.api.body.UserBody;
import cn.example.moment.pojo.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user_info")
    public List<UserEntity> findAll();

    @Select("select * from user_info where id=#{id}")
    public UserEntity findUser(Long id);

    @Select("select *from user_info where username=#{username}")
    public UserEntity queryUserByUsername(String username);
}
