package com.wiceflow.controller;


import com.wiceflow.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wiceflow.entity.mapper.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author BF
 * @date 2018/10/26
 */
@RestController
public class CheckController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("check_user")
    public final AjaxResult testCheckUserParam(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> list = result.getFieldErrors();
            Locale currentLocale = LocaleContextHolder.getLocale();
            for (FieldError fieldError : list) {
                String errorMessage = messageSource.getMessage(fieldError,currentLocale);
                System.out.println(errorMessage);
            }

            FieldError fieldError = result.getFieldError();
            System.out.println(fieldError.getDefaultMessage() + " 2");


        }
        return AjaxResult.okResponse("back");
    }
}
