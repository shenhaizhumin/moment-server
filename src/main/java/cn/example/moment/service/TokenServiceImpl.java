package cn.example.moment.service;

import cn.example.moment.pojo.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(UserEntity user) {
        String token = "";
        token = JWT.create().withAudience(user.getId() + "")
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
