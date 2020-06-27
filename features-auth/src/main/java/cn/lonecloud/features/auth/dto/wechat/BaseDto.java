package cn.lonecloud.features.auth.dto.wechat;

import lombok.Data;

/**
 * 基础类
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 19:48
 * @since v1.0
 */
@Data
public class BaseDto {


    private String errcode;

    private String errmsg;
}
