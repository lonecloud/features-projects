package cn.lonecloud.features.auth.util;


import cn.lonecloud.features.auth.config.WeChatAppConfig;
import cn.lonecloud.features.auth.dto.WeChatAppLoginDto;
import cn.lonecloud.features.auth.util.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * WeChatApp工具类
 *
 * @author lonecloud
 * @version v1.0
 * @date 2018/11/5 19:36
 */
@Component
public class WeChatAppTool {
    private Logger logger= LoggerFactory.getLogger(WeChatAppTool.class);

    @Autowired
    WeChatAppConfig weChatAppConfig;


    /**
     *
     * @return
     */
    public WeChatAppLoginDto login(String code){
        //判断code
        if (StringUtils.isNotBlank(code)){
            String url=weChatAppConfig.getUrl();
            String replaceUrl = url.replace("APPID", weChatAppConfig.getAppId())
                    .replace("SECRET", weChatAppConfig.getSecret())
                    .replace("JSCODE", code);
            return HttpClientUtil.doGetJson(replaceUrl, WeChatAppLoginDto.class);
        }
        return null;
    }
}
