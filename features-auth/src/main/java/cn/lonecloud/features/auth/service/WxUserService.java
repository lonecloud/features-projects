package cn.lonecloud.features.auth.service;

import cn.lonecloud.features.auth.dto.wechat.param.AppLoginParam;
import cn.lonecloud.features.auth.vo.WxLoginVo;

/**
 * 微信服务层
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 21:08
 * @since v1.0
 */
public interface WxUserService {
    WxLoginVo login(AppLoginParam param);
}
