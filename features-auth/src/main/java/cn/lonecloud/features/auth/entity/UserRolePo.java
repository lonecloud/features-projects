package cn.lonecloud.features.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色Po
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/13 23:55
 * @since v1.0
 */
@Data
public class UserRolePo {

    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 描述
     */
    private String desc;
    /**
     * 状态
     */
    private String status;


}
