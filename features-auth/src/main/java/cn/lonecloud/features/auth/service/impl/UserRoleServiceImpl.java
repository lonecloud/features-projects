package cn.lonecloud.features.auth.service.impl;

import cn.lonecloud.features.auth.entity.RoleEntity;
import cn.lonecloud.features.auth.mapper.RoleMapper;
import cn.lonecloud.features.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/20 23:03
 * @since v1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    RoleMapper roleMapper;
    @Override
    public String createRole(RoleEntity roleEntity) {
        int insert = roleMapper.insert(roleEntity);
        return "成功新增";
    }

    @Override
    public String updateRole(RoleEntity roleEntity) {
        int updateCount = roleMapper.updateById(roleEntity);

        return "成功更新"+updateCount+"条";
    }

    @Override
    public List<RoleEntity> listRole() {
        return roleMapper.listAll();
    }

    @Override
    public String deleteRoleById(String roleId) {
        return null;
    }

}
