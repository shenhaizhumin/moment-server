package cn.example.moment.interceptor;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.annotation.PassToken;
import cn.example.moment.exception.BusinessInterfaceException;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.UserService;
import cn.example.moment.utils.Constants;
import cn.example.moment.utils.Logger;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;

//https://www.cnblogs.com/shihaiming/p/9565835.html
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        Logger.logger.info("preHandle");
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(LoginRequired.class)) {
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            if (loginRequired.required()) {
                // 执行认证
                if (token == null) {
                    throw new BusinessInterfaceException(401, "无token，请重新登录");
                }
                // 获取 token 中的 user id
                String uid;
                try {
                    uid = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                if (!token.equals(redisTemplate.opsForValue().get(uid))) {
                    throw new BusinessInterfaceException(402, "token失效，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constants.SECRET)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new BusinessInterfaceException(401, "token解析失败，重新登录.");
                }
                MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
                if (null != methodParameters && methodParameters.length > 0 && methodParameters[0].hasParameterAnnotation(CurrentUser.class)) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("uid", uid);
                    UserEntity user = userService.getUser(params);
                    if (user == null) {
                        throw new BusinessInterfaceException(404, "用户不存在，请重新登录");
                    }
                    Logger.logger.info("@CurrentUser:" + user.getUsername());
                    httpServletRequest.setAttribute("currentUser", user);
                }

                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Logger.logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        Logger.logger.info("afterCompletion");
    }
}
