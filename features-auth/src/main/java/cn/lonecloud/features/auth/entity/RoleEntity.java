package cn.lonecloud.features.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/20 23:04
 * @since v1.0
 */
@TableName("auth_role")
@Data
public class RoleEntity {

    @TableId
    private Long roleId;

    private String roleCode;

    private String roleName;

    private String desc;

}
