package cn.lonecloud.features.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Gosn工具类
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/24 0:13
 * @since v1.0
 */
public class GsonUtils {
    private static final Gson gson= new GsonBuilder().create();

    public static String toJsonString(Object object){
        return gson.toJson(object);
    }

    public static <T> T parseObject(String string, Class<T> clazz) {
        return gson.fromJson(string,clazz);
    }
}
