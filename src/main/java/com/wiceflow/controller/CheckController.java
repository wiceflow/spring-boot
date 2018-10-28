package com.wiceflow.controller;


import com.wiceflow.util.AjaxResult;
import com.wiceflow.util.CheckParamAnnotation;
import com.wiceflow.util.ValidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wiceflow.entity.mapper.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author BF
 * @date 2018/10/26
 */
@RestController
public class CheckController {

    @Autowired
    private MessageSource messageSource;

    @CheckParamAnnotation
    @PostMapping(value = "check_user",produces = "application/json;charset=UTF-8")
    public AjaxResult testCheckUserParam(@RequestBody @Valid ValidList<User> user, BindingResult result) throws IOException {
        System.out.println("333");
        return AjaxResult.okResponse("back");
    }

}
