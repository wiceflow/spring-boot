package com.wiceflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author BF
 * @date 2018/10/23
 * 使用注解来注册 Servlet
 * 需要加上 @ServletComponentScan 注解 该注解的意思是 在 spring boot 启动时会扫描 @WebServlet，并将其实例化
 */
@SpringBootApplication
@ServletComponentScan
public class ServletAnnotationApplication {

    public static void main(String[] args) {
        System.out.println("ServletAnnotationApplication");
        SpringApplication.run(ServletAnnotationApplication.class, args);
    }
}
