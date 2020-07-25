package cn.lonecloud.features.auth.service.impl;

import cn.lonecloud.features.auth.entity.RolePo;
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
    public String createRole(RolePo rolePo) {
        int insert = roleMapper.insert(rolePo);
        return "成功新增";
    }

    @Override
    public String updateRole(RolePo rolePo) {
        int updateCount = roleMapper.updateById(rolePo);

        return "成功更新"+updateCount+"条";
    }

    @Override
    public List<RolePo> listRole() {
        return roleMapper.listAll();
    }

    @Override
    public int deleteRoleById(Long roleId) {
        RolePo rolePo=new RolePo();
        rolePo.setRoleId(roleId);
        rolePo.setStatus("00");
        return roleMapper.updateById(rolePo);
    }

}
