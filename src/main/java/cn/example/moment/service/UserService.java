package cn.example.moment.service;

import cn.example.moment.api.body.UserBody;
import cn.example.moment.pojo.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    //    List<UserEntity> findAll();
//
//    UserEntity findUser(Long id);
//
//    UserEntity queryUser(UserBody userBody);
    List<UserEntity> getAll();

    UserEntity getUserById(Long id);

    UserEntity getUser(Map<String,String> params);

    void insert(UserBody userBody);

    void update(UserEntity userEntity);

    void delete(Long id);
}
