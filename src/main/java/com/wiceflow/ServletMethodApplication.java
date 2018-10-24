package com.wiceflow;

import com.wiceflow.servlet.MethodServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author BF
 * @date 2018/10/23
 */
@SpringBootApplication
public class ServletMethodApplication {

    public static void main(String[] args) {
        System.out.println("ServletMethodApplication");
        SpringApplication.run(ServletMethodApplication.class,args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new MethodServlet());
        bean.addUrlMappings("/method");
        return bean;
    }
}
