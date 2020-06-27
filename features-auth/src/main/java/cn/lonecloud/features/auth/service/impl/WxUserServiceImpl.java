package cn.lonecloud.features.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import cn.lonecloud.features.auth.dto.wechat.WeChatAppLoginDto;
import cn.lonecloud.features.auth.dto.wechat.param.AppLoginParam;
import cn.lonecloud.features.auth.entity.UserInfoPo;
import cn.lonecloud.features.auth.entity.WxUserInfoPo;
import cn.lonecloud.features.auth.mapper.UserInfoMapper;
import cn.lonecloud.features.auth.mapper.WxUserInfoMapper;
import cn.lonecloud.features.auth.service.WxUserService;
import cn.lonecloud.features.auth.util.JwtUtils;
import cn.lonecloud.features.auth.util.WeChatAppTool;
import cn.lonecloud.features.auth.vo.WxLoginVo;
import cn.lonecloud.features.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 微信信息实现类
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 21:08
 * @since v1.0
 */
@Service
@Slf4j
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WeChatAppTool weChatAppTool;

    @Autowired
    private WxUserInfoMapper wxUserInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxLoginVo login(AppLoginParam param) {
        String code = param.getCode();
        WeChatAppLoginDto login = weChatAppTool.login(code);
        if (weChatAppTool.isSuccess(login)){
            QueryWrapper<WxUserInfoPo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("union_id",login.getUnionid())
            .eq("open_id",login.getOpenid());
            WxUserInfoPo wxUserInfoPo = wxUserInfoMapper.selectOne(queryWrapper);
            if (Objects.isNull(wxUserInfoPo)){
                wxUserInfoPo=generatorWxUser(login,param);
            }
            String token = JwtUtils.getToken(wxUserInfoPo.getUserId());
            //生成token，数据写入缓存中
            return WxLoginVo.builder().openId(login.getOpenid())
                    .token(token)
                    .build();
        }else {
            throw new BusinessException("登陆失败！！！");
        }
    }

    /**
     * 生成一个用户
     * @param login
     * @return
     */
    private Long generatorUser(WeChatAppLoginDto login) {
        String unionid = login.getUnionid();
        String userName="wx_"+unionid;
        UserInfoPo existUser = userInfoMapper.findUserByUserName(userName);
        if (Objects.isNull(existUser)){
            existUser=new UserInfoPo();
            existUser.setUsername(userName);
            existUser.setPassword(MD5.create().digestHex(RandomUtil.randomString(10)));
            existUser.setSalt(RandomUtil.randomString(10));
            userInfoMapper.insert(existUser);
            existUser = userInfoMapper.findUserByUserName(userName);
        }
        return existUser.getUserId();
    }
    public WxUserInfoPo generatorWxUser(WeChatAppLoginDto login, AppLoginParam param){
        Long userId = generatorUser(login);
        //进行插入数据
        String openid = login.getOpenid();
        String unionid = login.getUnionid();
        WxUserInfoPo build = WxUserInfoPo.builder()
                .avatarUrl(param.getAvatarUrl())
                .city(param.getCity())
                .country(param.getCountry())
                .gender(param.getGender())
                .nickName(param.getNickName())
                .openId(openid)
                .province(param.getProvince())
                .unionId(unionid)
                .userId(userId)
                .build();
        int insert = wxUserInfoMapper.insert(build);
        log.info("insert data into db count:{}",insert);
        //生成一个对应的用户信息
        return build;
    }
}
