package cn.lonecloud.features.auth.util;

import cn.lonecloud.features.auth.config.WeChatConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 检查类
 * @author lonecloud
 * @version v1.0
 * @date 下午1:00 2018/4/22
 */
@Component
public class CheckCodeTool {

    @Autowired
    private WeChatConfig weChatConfig;


    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = weChatConfig.getToken();
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("please set token");
        }
        String[] arr = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        //Sha1加密
        String s = SecureUtils.sha1(sb.toString());
        return s.equalsIgnoreCase(signature);
    }
}
