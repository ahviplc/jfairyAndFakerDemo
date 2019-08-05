package com.lc.demoapp.jfairyandfakerdemo;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@SpringBootApplication
@Controller
@Configuration
public class JfairyandfakerdemoApplication {

    /**
     * http://localhost:8080/hello
     *
     * @return
     */
    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello world！";
    }

    /**
     * http://localhost:8080/helloFaker
     *
     * @return
     */
    @RequestMapping("helloFaker")
    @ResponseBody
    public String helloFaker() {
        //设置 语言 ，地区
        Locale local = new Locale("zh", "CN");
        //创建对象
        Faker faker = new Faker(local);
        return faker.weather().description();
    }


    /**
     * 代码说明：
     * <p>
     * 1、@SpringBootApplication：Spring Boot项目的核心注解，主要目的是开启自动配置。；
     * <p>
     * 2、@Configuration：这是一个配置Spring的配置类；
     * <p>
     * 3、@Controller：标明这是一个SpringMVC的Controller控制器,知道springmvc的就不用解释了；
     * <p>
     * 4、main方法：在main方法中启动一个应用，即：这个应用的入口；
     * ---------------------
     * 作者：晓梦初醒p
     * 来源：CSDN
     * 原文：https://blog.csdn.net/csdn13257081409/article/details/78953510
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */

    public static void main(String[] args) {
        SpringApplication.run(JfairyandfakerdemoApplication.class, args);
    }

}
