package com.zhuimeng.controller.code;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/8/11.
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
        //这个注解可以提供一种方便的方式来将带有@ConfigurationProperties注解的类注入为Spring容器的Bean
public class SecurityCoreConfig {
}
