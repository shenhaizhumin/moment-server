package cn.example.moment.service;

import cn.example.moment.pojo.UserEntity;
import cn.example.moment.utils.Constants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(UserEntity user) {
        String token = "";
        token = JWT.create().withAudience(user.getId() + "")
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.ExpireTime))
//                .sign(Algorithm.HMAC256(user.getPassword())
                .sign(Algorithm.HMAC256(Constants.SECRET)
                );
        return token;
    }
}
