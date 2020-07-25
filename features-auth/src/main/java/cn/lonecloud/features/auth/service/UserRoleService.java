package cn.lonecloud.features.auth.service;

import cn.lonecloud.features.auth.entity.RoleEntity;

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
     * @param roleEntity
     * @return
     */
    String createRole(RoleEntity roleEntity);

    /**
     * 更新角色
     * @param roleEntity
     * @return
     */
    String updateRole(RoleEntity roleEntity);

    /**
     * 列举所有的角色
     * @return
     */
    List<RoleEntity> listRole();

    String deleteRoleById(String roleId);
}