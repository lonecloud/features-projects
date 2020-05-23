package cn.lonecloud.features.auth.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.lonecloud.features.auth.entity.UserInfoPo;
import cn.lonecloud.features.auth.mapper.UserInfoMapper;
import cn.lonecloud.features.auth.param.LoginParam;
import cn.lonecloud.features.auth.service.UserService;
import cn.lonecloud.features.auth.util.JwtUtils;
import cn.lonecloud.features.auth.vo.LoginUser;
import cn.lonecloud.features.common.exception.BusinessException;
import cn.lonecloud.features.common.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户服务层
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:57
 * @since v1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public LoginUser login(LoginParam param) {
        log.info("login info is {}", GsonUtils.toJsonString(param));
        UserInfoPo userInfoPo=userInfoMapper.findUserByUserName(param.getUsername());
        if (Objects.isNull(userInfoPo)){
            throw new BusinessException("用户名不存在！！");
        }
        //将密码进行md5
        String hex = MD5.create().digestHex(param.getPassword());
        if (StringUtils.equals(hex,userInfoPo.getPassword())){
            return LoginUser.builder()
                    .token(JwtUtils.getToken(userInfoPo))
                    .username(userInfoPo.getUsername())
                    .build();
        }
        throw new BusinessException("login error, maybe username or password error!!!");
    }

    @Override
    public UserInfoPo findUserById(String userId) {
        return userInfoMapper.selectById(Long.parseLong(userId));
    }
}
