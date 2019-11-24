package com.baoyong.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主方法
 * @author byJiang
 * @title MainApplication
 * @date 2019/11/20
 **/
@SpringBootApplication
@RestController
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainApplication.class);
        // 关闭一开始出现的图标文字
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        String salt = BCrypt.gensalt();
        String result = BCrypt.hashpw("12345", salt);
        System.out.println("salt:" + salt + ", result:" + result);
    }

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

}
