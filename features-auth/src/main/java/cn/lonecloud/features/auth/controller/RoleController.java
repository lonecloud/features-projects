package cn.lonecloud.features.auth.controller;

import cn.lonecloud.features.auth.entity.RoleEntity;
import cn.lonecloud.features.auth.service.UserRoleService;
import cn.lonecloud.features.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制层
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/20 22:59
 * @since v1.0
 */
@RestController("/auth/role")
public class RoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/")
    public Result createRole(RoleEntity roleEntity){
        return Result.success(userRoleService.createRole(roleEntity));
    }
    @PutMapping("/")
    public Result updateRole(RoleEntity roleEntity){
        return Result.success(userRoleService.updateRole(roleEntity));
    }
    @GetMapping("/")
    public Result getRole(){
        return Result.success(userRoleService.listRole());
    }
    public Result deleteRole(String roleId){
        return Result.success(userRoleService.deleteRoleById(roleId));
    }
}
