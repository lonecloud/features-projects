package cn.lonecloud.features.auth.schedule;

import cn.lonecloud.features.auth.dto.wechat.AppTokenDto;
import cn.lonecloud.features.auth.util.WeChatAppTool;
import cn.lonecloud.features.common.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 微信刷新token
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 19:42
 * @since v1.0
 */
@Slf4j
@Component
public class WxAppTokenSchedule {

    @Autowired
    private WeChatAppTool weChatAppTool;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void scheduleToken(){
        AppTokenDto token = weChatAppTool.getToken();
        log.info("using get token is {}", GsonUtils.toJsonString(token));
    }

}
