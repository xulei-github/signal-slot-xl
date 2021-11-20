package com.xl.signalslotxl.controller;

import com.xl.signalslotxl.service.SignalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/17 10:31
 */
@RestController
public class TestController {
    @Autowired
    SignalTest test;
    @RequestMapping("/test")
    public void tet(){
        test.Method_1("         xuleicceshi     ");
    }
    
    @RequestMapping("/test2")
    public void tet2(){
        test.Method_2("         xuleicceshi2     ");
    }
}
