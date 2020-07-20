package cn.lonecloud.features.auth.filter;

import cn.lonecloud.features.auth.annotation.PasswordToken;
import cn.lonecloud.features.auth.annotation.RequiresPermissions;
import cn.lonecloud.features.auth.cts.GlobalConstants;
import cn.lonecloud.features.auth.entity.UserInfoPo;
import cn.lonecloud.features.auth.service.UserService;
import cn.lonecloud.features.common.exception.NeedLoginException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 权限拦截器
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:43
 * @since v1.0
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从 http 请求头中取出 token
        String token = request.getHeader(GlobalConstants.TOKEN_STR);
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PasswordToken.class)) {
            PasswordToken passToken = method.getAnnotation(PasswordToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (method.isAnnotationPresent(RequiresPermissions.class)) {
            RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
            if (requiresPermissions.required()){
                if (StringUtils.isBlank(token)){
                    throw new NeedLoginException("NEED LOGIN");
                }
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (Exception e){
                    throw new NeedLoginException("encode token error!!!");
                }
                UserInfoPo user=userService.findUserById(Long.valueOf(userId));
                if(Objects.isNull(user)){
                    throw new NeedLoginException("NEED LOGIN");
                }
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getSalt())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new NeedLoginException("401");
                }
                //check auth
            }
        }

        return true;
    }
}
