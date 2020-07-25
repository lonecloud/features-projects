package cn.lonecloud.features.auth.service;

import cn.lonecloud.features.auth.entity.RolePo;

import java.util.List;

/**
 * 角色Service
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/13 23:54
 * @since v1.0
 */
public interface UserRoleService {
    /**
     * 创建role
     * @param rolePo
     * @return
     */
    String createRole(RolePo rolePo);

    /**
     * 更新角色
     * @param rolePo
     * @return
     */
    String updateRole(RolePo rolePo);

    /**
     * 列举所有的角色
     * @return
     */
    List<RolePo> listRole();

    int deleteRoleById(Long roleId);
}
