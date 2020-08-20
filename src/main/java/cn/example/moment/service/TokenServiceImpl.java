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
    public String getToken(String uid) {
        String token = "";
        token = JWT.create().withAudience(uid)
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.ExpireTime))
//                .sign(Algorithm.HMAC256(user.getPassword())
                .sign(Algorithm.HMAC256(Constants.SECRET)
                );
        return token;
    }
}
