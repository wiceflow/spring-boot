package com.wiceflow.controller;

import com.wiceflow.util.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BF
 * @date 2018/10/17
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public final AjaxResult getHelloWord(){
        String str = "Hello Word";
        return AjaxResult.okDataResponse(str);
    }
}
