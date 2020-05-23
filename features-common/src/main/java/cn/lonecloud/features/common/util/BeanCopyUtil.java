package cn.lonecloud.features.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * 复制类
 *
 * @author lonecloud
 * @Title: BeanCopyUtil.java
 * @Description:
 * @date 2016年12月9日 下午3:05:00
 */
public class BeanCopyUtil {

    private static Logger logger = LoggerFactory.getLogger(BeanCopyUtil.class);

    /**
     * 获取空字符串
     *
     * @param source
     * @return
     * @Description:
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝
     *
     * @param src
     * @param target
     * @Description:
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 拷贝
     *
     * @param src
     * @param target
     * @Description:
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target, String... exclude) {
        String[] propertyNames = getNullPropertyNames(src);
        List<String> list = new ArrayList<>();
        if (propertyNames != null) {
            List<String> asList = Arrays.asList(propertyNames);
            list.addAll(asList);
        }

        if (exclude != null) {
            for (String string : exclude) {
                list.add(string);
            }
        }
        String[] result = new String[list.size()];
        list.toArray(result);
        BeanUtils.copyProperties(src, target, result);
    }

    /**
     * 获取新实例
     *
     * @param src
     * @param clazz
     * @param exclude
     * @param <T>
     * @return
     */
    public static <T> T copyNewInstance(Object src, Class<T> clazz, String... exclude) {
        T t = null;
        try {
            t = clazz.newInstance();
            copyPropertiesIgnoreNull(src, t, exclude);
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("instance error", e);
        }
        return t;
    }

}
