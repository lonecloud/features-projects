package cn.lonecloud.features.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验注解
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/23 23:47
 * @since v1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordToken {

    boolean required() default true;

}
