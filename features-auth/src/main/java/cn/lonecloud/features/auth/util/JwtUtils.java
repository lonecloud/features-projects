package cn.lonecloud.features.auth.util;

import cn.lonecloud.features.auth.entity.UserInfoPo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * jwt工具类
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/24 0:23
 * @since v1.0
 */
public class JwtUtils {
    /**
     * 获取token信息
     * @param user
     * @return
     */
    public static String getToken(UserInfoPo user) {
        String token = "";
        token = JWT.create().withAudience(user.getUserId()+"")
                .sign(Algorithm.HMAC256(user.getSalt()));
        return token;
    }
}
