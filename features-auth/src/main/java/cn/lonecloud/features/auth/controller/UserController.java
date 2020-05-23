package cn.lonecloud.features.auth.controller;

import cn.lonecloud.features.auth.param.LoginParam;
import cn.lonecloud.features.auth.service.UserService;
import cn.lonecloud.features.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块
 *
 * @author lonecloud
 * @version v1.0
 * @date 下午1:52 2018/1/3
 */
@Controller
@RestController("/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 登录界面
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated LoginParam param) {
        return Result.success(userService.login(param));
    }

}
