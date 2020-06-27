package cn.lonecloud.features.auth.dto.wechat;

import lombok.Data;

/**
 * 获取WeChat数据信息
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 19:46
 * @since v1.0
 */
@Data
public class AppTokenDto extends BaseDto{

    private String access_token;

    private Long expires_in;
}
