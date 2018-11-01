package com.wiceflow.exception;

import com.wiceflow.util.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public AjaxResult handleRRException(RRException e){
		logger.error("异常统一管理处打印异常信息：" + e.getMessage());
		if (e.getCode() != 500){
		    return AjaxResult.customResponse(e.getCode(),e.getMessage(),null);
        }
		return AjaxResult.errorResponse(e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public AjaxResult handleException(Exception e){
		// 控制台打印错误信息
		e.printStackTrace();
		return AjaxResult.errorResponse(e.getMessage());
	}
}
