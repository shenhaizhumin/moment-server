package cn.example.moment.service;

import cn.example.moment.api.body.UserBody;
import cn.example.moment.dao.UserMapper;
import cn.example.moment.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> users = userMapper.findAll();
        System.out.println(users);
        return users;
    }

    @Override
    public UserEntity findUser(Long id) {
        return userMapper.findUser(id);
    }

    @Override
    public UserEntity queryUser(UserBody userBody) {
        return userMapper.queryUserByUsername(userBody.getUsername());
    }
}
