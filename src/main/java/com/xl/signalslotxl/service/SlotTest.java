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
        System.out.println(Thread.currentThread().getName());
        System.out.println("SlotTest.fun_1" + str);
    }
    
    @Connect(clazz = SignalTest.class, signal = "Method_1,Method_2")
    public void fun_2(String str) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("SlotTest.fun_2" + str);
    }
    
    @Connect(clazz = SignalTest.class, signal = "Method_1,Method_2")
    public void fun_3(String str) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("SlotTest.fun_3" + str);
    }
}
