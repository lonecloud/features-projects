package cn.lonecloud.features.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/4/27 22:51
 * @since v1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.lonecloud.features")
@MapperScan(basePackages = "cn.lonecloud.features.auth.mapper")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


}
