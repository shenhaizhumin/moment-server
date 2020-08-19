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
    public List<UserEntity> getAll() {
        return userMapper.getAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void insert(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }

    @Override
    public void update(UserEntity userEntity) {
        userMapper.update(userEntity);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

//    @Override
//    public List<UserEntity> findAll() {
//        List<UserEntity> users = userMapper.findAll();
//        System.out.println(users);
//        return users;
//    }
//
//    @Override
//    public UserEntity findUser(Long id) {
//        return userMapper.findUser(id);
//    }
//
//    @Override
//    public UserEntity queryUser(UserBody userBody) {
//        return userMapper.queryUserByUsername(userBody.getUsername());
//    }
}
