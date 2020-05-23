package cn.lonecloud.features.common.entity;


import cn.lonecloud.features.common.cts.RCode;
import lombok.Data;

import java.text.MessageFormat;

/**
* @Title: R.java
* @Description: json页面对象
* @author lonecloud
* @date 2018/1/4 下午6:44
* @version V1.0
*/
@Data
public class Result {

    private int status;

    private String msg;

    private Object data;

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(int status, String msg, Object data) {
        this.status=status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 判断是否是成功返回
     *
     * @return
     */
    public boolean isSuccess() {
        return status == RCode.SUCCESS.getCode();
    }

    public static  Result success() {
        return new Result(RCode.SUCCESS.getCode());
    }

    public static  Result success(String msg) {
        return new Result(RCode.SUCCESS.getCode(), msg);
    }

    public static  Result success(String msg, Object data) {
        return new Result(RCode.SUCCESS.getCode(), msg, data);
    }

    public static  Result success(Object data) {
        return new Result(RCode.SUCCESS.getCode(), RCode.SUCCESS.getDesc(), data);
    }

    public static  Result error() {
        return new Result(RCode.ERROR.getCode(), RCode.ERROR.getDesc());
    }

    public static  Result error(String errorMsg) {
        return new Result(RCode.ERROR.getCode(), errorMsg);
    }

    public static  Result error(String errorMsg, Object... param) {
        return new Result(RCode.ERROR.getCode(), MessageFormat.format(errorMsg,param));
    }


    public static  Result error(int status, String errorMsg) {
        return new Result(status, errorMsg);
    }

    public static  Result error(RCode rCode) {
        return new Result(rCode.getCode(), rCode.getDesc());
    }

}
