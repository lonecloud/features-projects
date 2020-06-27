package cn.lonecloud.features.auth.dto.wechat;

import lombok.Data;

/**
 * @author lonecloud
 * @version v1.0
 * @date 2018/11/5 19:38
 */
@Data
public class WeChatAppLoginDto extends BaseDto{

    private String openid;

    private String session_key;

    private String unionid;
}
