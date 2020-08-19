package cn.example.moment.service;

import cn.example.moment.api.body.UserBody;
import cn.example.moment.pojo.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();

    UserEntity findUser(Long id);

    UserEntity queryUser(UserBody userBody);
}
