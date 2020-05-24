package cn.lonecloud.features.auth.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息参数
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/24 10:26
 * @since v1.0
 */
@Data
public class UserInfoParam {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
