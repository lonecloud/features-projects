package cn.lonecloud.features.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import cn.lonecloud.features.auth.entity.RolePo;
import cn.lonecloud.features.auth.entity.UserInfoPo;
import cn.lonecloud.features.auth.mapper.UserInfoMapper;
import cn.lonecloud.features.auth.mapper.UserRoleMapper;
import cn.lonecloud.features.auth.param.LoginParam;
import cn.lonecloud.features.auth.param.UserInfoParam;
import cn.lonecloud.features.auth.service.UserService;
import cn.lonecloud.features.auth.util.JwtUtils;
import cn.lonecloud.features.auth.vo.LoginUser;
import cn.lonecloud.features.auth.vo.PermissionVo;
import cn.lonecloud.features.common.exception.BusinessException;
import cn.lonecloud.features.common.exception.NeedLoginException;
import cn.lonecloud.features.common.util.GsonUtils;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class UserServiceImpl extends ServiceImpl<UserInfoMapper,UserInfoPo> implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public String login(LoginParam param) {
        log.info("login info is {}", GsonUtils.toJsonString(param));
        UserInfoPo userInfoPo=userInfoMapper.findUserByUserName(param.getUsername());
        if (Objects.isNull(userInfoPo)){
            throw new NeedLoginException("用户名不存在！！");
        }
        //将密码进行md5
        String hex = MD5.create().digestHex(param.getPassword());
        if (StringUtils.equals(hex,userInfoPo.getPassword())){
            return JwtUtils.getToken(userInfoPo);
        }
        throw new NeedLoginException("login error, maybe username or password error!!!");
    }

    @Override
    public UserInfoPo findUserById(Long userId) {
        return userInfoMapper.selectById(userId);
    }

    @Override
    public int register(UserInfoParam param) {
        int count = userInfoMapper.countUserByUserName(param.getUsername());
        if (count>0){
            throw new BusinessException("该用户名已经被使用！！");
        }
        UserInfoPo userInfoPo=new UserInfoPo();
        userInfoPo.setUsername(param.getUsername());
        userInfoPo.setPassword(MD5.create().digestHex(param.getPassword()));
        userInfoPo.setSalt(RandomUtil.randomString(10));
        int insert = userInfoMapper.insert(userInfoPo);
        log.info("create new user count【{}】",insert);
        return insert;
    }

    @Override
    public LoginUser findUserByToken(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        UserInfoPo userInfoPo = userInfoMapper.selectById(userId);
        List<RolePo> roles = userRoleMapper.getRoleByUserId(Long.valueOf(userId));
        //开始获取权限
        List<PermissionVo> permissions= userRoleMapper.selectPermissionByUserId(userInfoPo.getUserId());
        return LoginUser.builder()
                .roles(roles)
                .name(userInfoPo.getUsername())
                .permissions(permissions)
                .build();
    }
}
