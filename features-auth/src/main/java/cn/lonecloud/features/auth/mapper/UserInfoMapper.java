package cn.lonecloud.features.auth.mapper;

import cn.lonecloud.features.auth.entity.UserInfoPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/24 0:05
 * @since v1.0
 */
public interface UserInfoMapper  extends BaseMapper<UserInfoPo> {
    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    @Select("select * from auth_user_info where status = '01' and username = #{username}")
    UserInfoPo findUserByUserName(String username);

    @Select("select  count(1) from  auth_user_info where status = '01' and username = #{username}")
    int countUserByUserName(String username);
}
