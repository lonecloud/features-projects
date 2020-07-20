package cn.lonecloud.features.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信信息表
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 21:19
 * @since v1.0
 */
@Data
@TableName("auth_wx_user_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxUserInfoPo {

    private String openId;

    private String unionId;
    /**
     * 关联用户Id
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 图片url
     */
    private String avatarUrl;
    /**
     * 性别 0：未知、1：男、2：女
     */
    private String gender;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 国家
     */
    private String country;

}
