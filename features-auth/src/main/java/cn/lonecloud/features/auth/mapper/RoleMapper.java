package cn.lonecloud.features.auth.mapper;

import cn.lonecloud.features.auth.entity.RolePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/7/20 23:04
 * @since v1.0
 */
public interface RoleMapper extends BaseMapper<RolePo> {

    @Select("select * from auth_role where status='01'")
    public List<RolePo> listAll();
}
