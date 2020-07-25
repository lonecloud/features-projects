package cn.lonecloud.features.auth.mapper;

import cn.lonecloud.features.auth.entity.RolePo;
import cn.lonecloud.features.auth.entity.UserRolePo;
import cn.lonecloud.features.auth.vo.PermissionVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/13 23:55
 * @since v1.0
 */
public interface UserRoleMapper {
    @Select("select p.permission_code,p.type,p.content from auth_permission p ,auth_role_permission rp ,auth_role r ,auth_user_role ur where p.permission_id=rp.permission_id and rp.role_id=r.role_id and ur.role_id=r.role_id and  ur.user_id =#{userId}")
    List<PermissionVo> selectPermissionByUserId(Long userId);


    @Select("select role_code from auth_role r , auth_user_role ur where ur.role_id=r.role_id and user_id=#{userId}")
    List<RolePo> getRoleByUserId(Long userId);

}
