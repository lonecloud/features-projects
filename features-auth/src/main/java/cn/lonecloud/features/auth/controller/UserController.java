package cn.lonecloud.features.auth.controller;

import cn.lonecloud.features.auth.annotation.RequiresPermissions;
import cn.lonecloud.features.auth.param.LoginParam;
import cn.lonecloud.features.auth.param.UserInfoParam;
import cn.lonecloud.features.auth.service.UserService;
import cn.lonecloud.features.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户模块
 *
 * @author lonecloud
 * @version v1.0
 * @date 下午1:52 2018/1/3
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/info")
    @RequiresPermissions("base")
    public Result userInfo(String  token){
        return Result.success(userService.findUserByToken(token));
    }
    /**
     * 登录界面
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginParam param) {
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",userService.login(param));
        return Result.success(tokenMap);
    }

    /**
     * 注册
     * @param param
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserInfoParam param){
        return Result.success(userService.register(param));
    }
}
