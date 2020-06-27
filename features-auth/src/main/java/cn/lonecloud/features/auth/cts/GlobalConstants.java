package cn.lonecloud.features.auth.cts;

/**
 * 全局常量
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:44
 * @since v1.0
 */
public class GlobalConstants {

    public static final String TOKEN_STR="x_token";
    /**
     * 获取token
     */
    public static final String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
}
