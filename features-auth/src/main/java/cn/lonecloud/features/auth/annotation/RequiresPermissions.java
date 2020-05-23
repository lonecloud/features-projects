package cn.lonecloud.features.auth.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:41
 * @since v1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {

    boolean required() default true;

    String value() ;
}
