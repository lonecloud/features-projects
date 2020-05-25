package cn.lonecloud.features.auth.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lonecloud
 * @version v1.0
 * @Package cn.lonecloud.sms.wechat.config
 * @Description: config配置文件
 * @date 2018/11/6下午11:55
 */
@Configuration
@EnableConfigurationProperties(value = WeChatAppConfig.class)
public class WeChatAutoConfiguration {
}
