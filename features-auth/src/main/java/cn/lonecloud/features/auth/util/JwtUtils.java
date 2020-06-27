package cn.lonecloud.features.auth.util;

import cn.hutool.core.util.RandomUtil;
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
       return getToken(user.getUserId());
    }
    public static String getToken(Long userId) {
        String token = "";
        token = JWT.create().withAudience(userId+"")
                .sign(Algorithm.HMAC256(RandomUtil.randomString(10)));
        return token;
    }
}
