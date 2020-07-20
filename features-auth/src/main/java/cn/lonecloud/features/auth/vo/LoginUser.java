package cn.lonecloud.features.auth.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 登陆用户信息
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:53
 * @since v1.0
 */
@Data
@Builder
public class LoginUser {

    /**
     * 用户名
     */
    private String username;

    private String token;

    private List<PermissionVo> permissions;




}
