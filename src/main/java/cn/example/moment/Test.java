package cn.example.moment;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.qfd0G-elhE1aGr15LrnYlIZ_3UToaOM5HeMcXrmDGBM

        System.out.println(JWT.create().withAudience("1")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 5))
                .sign(Algorithm.HMAC256("123456")));
//        Algorithm.

    }
}
