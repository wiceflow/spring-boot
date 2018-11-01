package com.wiceflow.controller;

import com.wiceflow.entity.mapper.User;
import com.wiceflow.entity.vo.ParamList;
import com.wiceflow.util.AjaxResult;
import com.wiceflow.util.CheckParamAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

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
    public AjaxResult testCheckUserParam(@RequestBody @Valid ParamList<User> user, BindingResult result) throws IOException {
        System.out.println("333");
        return AjaxResult.okResponse("back");
    }


    /**
     * 这会出现异常
     * An Errors/BindingResult argument is expected to be declared immediately after the model attribute
     * @param abc
     * @param result
     * @return
     */
    @GetMapping(value = "check_param")
    public AjaxResult testCheckParam(@NotNull @RequestParam(value = "abc",required = false)String abc,BindingResult result) {
        if (result.hasErrors()){
            FieldError fieldError = result.getFieldError();
            System.out.println(fieldError.getDefaultMessage());
        }
        return AjaxResult.okResponse("back");
    }


    @PostMapping(value = "check_json",produces = "application/json;charset=UTF-8")
    public AjaxResult testCheckParamJSON( @RequestParam(value = "age",required = false)String age,
                                          @RequestParam(value = "name",required = false)String name) {
        System.out.println(age);
        System.out.println(name);
        return AjaxResult.okResponse("back");
    }

}
