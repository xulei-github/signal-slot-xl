package com.xl.signalslotxl.service;

import com.xl.signalslotxl.anno.Signal;
import com.xl.signalslotxl.anno.SignalMethod;
import org.springframework.stereotype.Component;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/16 17:30
 */
@Component
@Signal
public class SignalTest01 {
    @SignalMethod(methodName = "Method_1")
    public void Method_1(String value) {
        System.out.println("SignalTest.methond_1" + value);
    }

    //    @SignalMethod
    @SignalMethod
    public void Method_2(String value) {
        System.out.println("调用SignalTest类的methond_2方法" + value);
    }

}
