package com.xl.signalslotxl.controller;

import com.xl.signalslotxl.service.SignalTest;
import com.xl.signalslotxl.service.SignalTest01;
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
    @Autowired
    SignalTest01 test01;
    @RequestMapping("/test")
    public void tet(){
        test.Method_1("         徐磊测试001    ");
    }
    
    @RequestMapping("/test2")
    public void tet2(){
        test01.Method_2("         徐磊测试111     ");
    }
}
