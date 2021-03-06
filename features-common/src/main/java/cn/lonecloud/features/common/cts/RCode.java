package cn.lonecloud.features.common.cts;

/**
* @Title: RCode.java
* @Description: json 返回页面状态
* @author lonecloud
* @date 2018/1/4 下午6:53
* @version V1.0
*/
public enum RCode {

    SUCCESS(200,"成功！"),
    ERROR(500,"ERROR"),
    NEED_LOGIN(401,"NEED_LOGIN"),
    BUSINESS(406,"BUSINESS EX"),
    ILLEGAL_ARGUMENT(402,"ILLEGAL_ARGUMENT");

    private final int code;

    private final String desc;

    RCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
