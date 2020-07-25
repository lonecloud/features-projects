package cn.lonecloud.features.auth.service;

import cn.lonecloud.features.auth.entity.UserInfoPo;
import cn.lonecloud.features.auth.param.LoginParam;
import cn.lonecloud.features.auth.param.UserInfoParam;
import cn.lonecloud.features.auth.vo.LoginUser;

/**
 * 用户信息
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/4/27 23:35
 * @since v1.0
 */
public interface UserService {
    /**
     * 登陆
     * @param param
     * @return
     */
    String login(LoginParam param);

    /**
     * 通过用户Id 查找对应的登陆用户
     * @param userId
     * @return
     */
    UserInfoPo findUserById(Long userId);

    int register(UserInfoParam param);

    LoginUser findUserByToken(String token);
}
