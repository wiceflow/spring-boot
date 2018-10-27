package com.wiceflow.controller;


import com.wiceflow.util.AjaxResult;
import com.wiceflow.util.CheckParamAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wiceflow.entity.mapper.*;

import javax.validation.Valid;

/**
 * @author BF
 * @date 2018/10/26
 */
@RestController
public class CheckController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("check_user")
    public AjaxResult testCheckUserParam(@Valid User user, BindingResult result) {
        System.out.println("aaaaa");

        System.out.println("bbbbb");
        return AjaxResult.okResponse("back");
    }
}
