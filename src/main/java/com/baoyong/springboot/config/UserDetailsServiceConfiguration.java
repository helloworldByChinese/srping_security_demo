package com.baoyong.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * 自定义的Configuration , 代替UserDetailsServiceAutoConfiguration
 * 进行UserDetailsService实现类的注册，注入spring容器
 * @author byJiang
 * @title UserDetailsServiceConfiguration
 * @date 2019/11/24
 **/
@Configuration
public class UserDetailsServiceConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager() {
        UserDetailsServiceAndManager userDetailsManager = new UserDetailsServiceAndManager();
        return userDetailsManager;
    }

}
