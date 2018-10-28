package com.wiceflow.util;

import com.alibaba.fastjson.JSONObject;
import com.wiceflow.exception.RRException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 * @author BF
 * @date 2018/10/27
 */
//@Aspect     // 声明这是一个切面类
//@Order(1)   // 设置切面优先级:如果多个切面,可通过优先级控制斜面的执行顺序(数值越小,优先级越高)
//@Component  // 把 ParameterVerificationAspectJ 注册成bean,放到IOC容器中
public class ParameterVerificationAspectJ {
    /**
     * slf4j 日志接口
     */
    private static final Logger logger = LoggerFactory.getLogger(ParameterVerificationAspectJ.class);

    /**
     * 切面切入点表达式    引用的时候记得修改这里
     */
    private static final String ASPECT_POINTCUT_EXPRESSION = "execution(public * com.wiceflow.controller..*.*(..))";

    /**
     * 定义一个方法用于声明切入点表达式,后面增强方法需要注解引用改方法名
     */
    @Pointcut(ASPECT_POINTCUT_EXPRESSION)
    public void aspectMethod() {

    }


    /**
     * 环绕增强，在某一方法的前后执行
     *
     * @param joinPoint  通知参数 环绕增强需使用  ProceedingJoinPoint 它是 JoinPoint 的子类
     * @return           返回方法执行结果
     * @throws Throwable 异常抛出
     */
    @Around(value = "aspectMethod()")
    public Object around(ProceedingJoinPoint  joinPoint) throws Throwable {
        Object result = null;
        // 反射获取方法信息
        Class<?>[] params = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        // 代理方法信息
        String methodName = joinPoint.getSignature().getName();
        // 操作描述  这里反射获取注解  -- 获取是否开启参数校验注解
        CheckParamAnnotation checkParamAnnotation = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, params)
                .getAnnotation(CheckParamAnnotation.class);
        // 判断是否被注解类注解
        if (checkParamAnnotation == null) {
            result = joinPoint.proceed(joinPoint.getArgs());
            return result;
        }
        // 判断参数是否不合法
        Boolean isError = false;
        StringBuilder errorMessage = new StringBuilder();
        try {
            // 从上下文中获取request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            for (Object param : joinPoint.getArgs()) {
                // BindingResult 是 配合 Hibernate 的 @Valid 来使用的
                if (param instanceof BindingResult){
                    BindingResult bindingResult = (BindingResult) param;
                    // 如果参数校验发生了错误 则随机选取错误信息返回
                    if (bindingResult.hasErrors()) {
                        // 请求的url
                        logger.error("【---------------------------------我是一条分隔符----------------------------------------】");
                        logger.error("请求url： " + request.getRequestURL());
                        logger.error("请求api： " + request.getRequestURI());
                        // 请求的ip
                        logger.error("请求ip ：" + request.getRemoteAddr() + "、" + request.getHeader("x-forwarded-for") + "、"
                                + request.getHeader("Proxy-Client-IP") + "、" + request.getHeader("WL-Proxy-Client-IP"));
                        // 这里不需要遍历数组，抛出其中一个就可以了  （源码默认遍历第一个）
                        FieldError fieldError = bindingResult.getFieldError();
                        String message = fieldError.getDefaultMessage();
                        logger.error("此次请求参数校验不通过，具体信息：" + message);
                        errorMessage.append(message);
                        isError = true;
                        break;
                    }
                }
            }
            if (isError){
                throw new RRException(errorMessage.toString(),401);
            }else {
                return joinPoint.proceed(joinPoint.getArgs());
            }
        } finally {
            // TODO 这里可以做日志入库处理

        }
    }

}
