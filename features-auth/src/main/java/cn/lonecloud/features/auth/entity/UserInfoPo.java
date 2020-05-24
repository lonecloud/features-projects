package cn.lonecloud.features.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:59
 * @since v1.0
 */
@TableName("base_user_info")
@Data
public class UserInfoPo {

    @TableId
    private Long userId;

    private String username;

    private String password;

    private String salt;
    /**
     * 状态
     */
    private String status;

}
