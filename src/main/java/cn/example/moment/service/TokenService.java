package cn.example.moment.service;

import cn.example.moment.pojo.UserEntity;
import org.springframework.stereotype.Service;


public interface TokenService {
    String getToken(UserEntity user);
}
