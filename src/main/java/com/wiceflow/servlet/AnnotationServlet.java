package com.wiceflow.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BF
 * @date 2018/10/23
 * Spring Boot 使用注解方式整合 Servlet
 *          拓展知识
 */
@WebServlet(name = "annotationServlet",urlPatterns = "/annotation")
public class AnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Annotation Servlet ...");
    }
}
