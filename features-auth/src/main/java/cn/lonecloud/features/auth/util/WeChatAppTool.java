package cn.lonecloud.features.auth.util;


import cn.lonecloud.features.auth.config.WeChatAppConfig;
import cn.lonecloud.features.auth.cts.GlobalConstants;
import cn.lonecloud.features.auth.dto.wechat.AppTokenDto;
import cn.lonecloud.features.auth.dto.wechat.BaseDto;
import cn.lonecloud.features.auth.dto.wechat.WeChatAppLoginDto;
import cn.lonecloud.features.auth.util.http.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * WeChatApp工具类
 *
 * @author lonecloud
 * @version v1.0
 * @date 2018/11/5 19:36
 */
@Component
@Slf4j
public class WeChatAppTool {

    @Autowired
    WeChatAppConfig weChatAppConfig;


    /**
     * token
     */
    private volatile String tokenStr = null;
    /**
     * token过期时间
     */
    private volatile Long tokenExpireTime = null;

    /**
     * @return
     */
    public WeChatAppLoginDto login(String code) {
        //判断code
        if (StringUtils.isNotBlank(code)) {
            String url = weChatAppConfig.getUrl();
            String replaceUrl = url.replace("APPID", weChatAppConfig.getAppId())
                    .replace("SECRET", weChatAppConfig.getSecret())
                    .replace("JSCODE", code);
            return HttpClientUtil.doGetJson(replaceUrl, WeChatAppLoginDto.class);
        }
        return null;
    }

    /***
     * 获取缓存
     * @return
     */
    public String getCacheToken() {
        if (tokenStr == null||tokenExpireTime<=System.currentTimeMillis()) {
            //1.请求获取token
            AppTokenDto appTokenDto = getToken();
            if (isSuccess(appTokenDto)) {
                synchronized (this) {
                    tokenStr = appTokenDto.getAccess_token();
                    tokenExpireTime = System.currentTimeMillis() + appTokenDto.getExpires_in() * 1000;
                }
            }
        }
        return tokenStr;
    }

    public AppTokenDto getToken() {
        String realTokenUrl = GlobalConstants.TOKEN_URL.replace("APPID", weChatAppConfig.getAppId())
                .replace("APPSECRET", weChatAppConfig.getSecret());
        return HttpClientUtil.doGetJson(realTokenUrl, AppTokenDto.class);
    }

    public boolean isSuccess(BaseDto baseDto) {
        if (Objects.isNull(baseDto)) {
            return false;
        }
        String errcode = baseDto.getErrcode();
        if (StringUtils.isBlank(errcode)||"0".equals(errcode)) {
            return true;
        } else {
            log.error("weChat tool invoke error!!! message is [{}]", baseDto.getErrmsg());
            return false;
        }
    }
}
