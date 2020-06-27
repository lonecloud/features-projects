package cn.lonecloud.features.auth.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 登陆返回数据
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 21:09
 * @since v1.0
 */
@Builder
@Data
public class WxLoginVo {
    /**
     * 自定义状态数据
     */
    private String token;
    /**
     * 返回openId
     */
    private String openId;
}
