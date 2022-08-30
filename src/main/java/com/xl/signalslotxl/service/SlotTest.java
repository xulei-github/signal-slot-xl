package com.xl.signalslotxl.service;

import com.xl.signalslotxl.anno.Connect;
import com.xl.signalslotxl.anno.Slot;
import org.springframework.stereotype.Component;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/16 17:30
 */
@Component
@Slot
public class SlotTest {
    @Connect(clazz = SignalTest.class, signal = "Method_1,Method_2")
    public void fun_1(String str) {
        System.out.println("线程"+Thread.currentThread().getName() + "调用SlotTest.fun_1方法,参数:" + str);
    }

    //    @Connect(clazz = SignalTest.class, signal = "Method_1,Method_2")
    public void fun_2(String str) {
        System.out.println("线程"+Thread.currentThread().getName() + "调用SlotTest.fun_2方法,参数:" + str);
    }

    @Connect(clazz = SignalTest01.class, signal = "Method_2")
    public void fun_3(String str) {
        System.out.println("线程"+Thread.currentThread().getName() + "调用SlotTest.fun_3方法,参数:" + str);
    }
}
