package cn.lonecloud.features.auth.dto.wechat.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登陆传递数据
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/6/27 20:50
 * @since v1.0
 */
@Data
public class AppLoginParam {

    @NotBlank
    private String code;
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
