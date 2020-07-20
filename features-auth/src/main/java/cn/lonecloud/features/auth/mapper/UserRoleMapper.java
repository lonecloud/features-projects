package cn.lonecloud.features.auth.mapper;

import cn.lonecloud.features.auth.vo.PermissionVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/13 23:55
 * @since v1.0
 */
public interface UserRoleMapper{
    @Select("select p.permission_code,p.type,p.content from auth_permission p ,auth_role_permission rp ,auth_role r  where p.permission_id=rp.permission_id and rp.role_id=r.role_id and user_id =#{userId}")
    List<PermissionVo> selectPermissionByUserId(Long userId);
}
