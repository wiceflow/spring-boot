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
		if (e.getCode() != 500){

		    return AjaxResult.customResponse(e.getCode(),e.getMessage(),null);
        }
		return AjaxResult.errorResponse(e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public AjaxResult handleException(Exception e){
//		logger.error(e.getMessage(), e);
		return AjaxResult.errorResponse(e.getMessage());
	}
}
