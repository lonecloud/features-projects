package cn.lonecloud.features.auth.param;

import lombok.Data;

/**
 * TODO
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/20 23:01
 * @since v1.0
 */
@Data
public class RoleParam {

    private String roleCode;

    private String roleName;

    private String desc;
}
