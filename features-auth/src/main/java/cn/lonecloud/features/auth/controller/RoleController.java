package cn.lonecloud.features.auth.controller;

import cn.lonecloud.features.auth.entity.RolePo;
import cn.lonecloud.features.auth.service.UserRoleService;
import cn.lonecloud.features.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result createRole(RolePo rolePo){
        return Result.success(userRoleService.createRole(rolePo));
    }
    @PutMapping("/")
    public Result updateRole(RolePo rolePo){
        return Result.success(userRoleService.updateRole(rolePo));
    }
    @GetMapping("/")
    public Result getRole(){
        return Result.success(userRoleService.listRole());
    }
    @DeleteMapping("/{roleId}")
    public Result deleteRole(@PathVariable("roleId") Long roleId){
        return Result.success(userRoleService.deleteRoleById(roleId));
    }
}
