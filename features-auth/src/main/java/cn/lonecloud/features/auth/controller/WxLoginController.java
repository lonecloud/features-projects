package cn.lonecloud.features.auth.controller;


import cn.lonecloud.features.auth.dto.WeChatAppLoginDto;
import cn.lonecloud.features.auth.util.CheckCodeTool;
import cn.lonecloud.features.auth.util.WeChatAppTool;
import cn.lonecloud.features.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制类
 * @author lonecloud
 * @version v1.0
 * @date 2018/11/5 13:46
 */
@RestController
@RequestMapping("/wx/app")
@Slf4j
public class WxLoginController {

    @Autowired
    private CheckCodeTool checkCodeTool;
    @Autowired
    WeChatAppTool weChatAppTool;

    @GetMapping
    public String signature(@RequestParam(name = "signature" ,required = false) String signature  ,
                            @RequestParam(name = "nonce",required = false) String  nonce ,
                            @RequestParam(name = "timestamp",required = false) String  timestamp ,
                            @RequestParam(name = "echostr",required = false) String  echostr) {
        if (checkCodeTool.checkSignature(signature,timestamp,nonce)) {
            return echostr;
        }
        return null;
    }
    @GetMapping("/login/{code}")
    public Result wxLogin(@PathVariable("code")String code){
        log.info(code);
        WeChatAppLoginDto login = weChatAppTool.login(code);
        log.info(login.toString());
        return Result.success("success");
    }
}
