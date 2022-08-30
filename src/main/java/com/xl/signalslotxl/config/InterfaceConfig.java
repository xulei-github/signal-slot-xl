package com.xl.signalslotxl.config;

import com.xl.signalslotxl.anno.Connect;
import com.xl.signalslotxl.anno.Signal;
import com.xl.signalslotxl.anno.SignalMethod;
import com.xl.signalslotxl.anno.Slot;
import com.xl.signalslotxl.constant.SignalSlotConstant;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/16 17:31
 */
@Configuration
public class InterfaceConfig implements ApplicationListener<ContextRefreshedEvent> {
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //实例名，该实例
        Map<String, Object> signalMap = event.getApplicationContext().getBeansWithAnnotation(Signal.class);
        //实例名
        String[] signalList = event.getApplicationContext().getBeanNamesForAnnotation(Signal.class);
        
        Map<String, Object> slotMap = event.getApplicationContext().getBeansWithAnnotation(Slot.class);
        String[] slotList = event.getApplicationContext().getBeanNamesForAnnotation(Slot.class);
        //赋值全局变量
        setConstantValue(signalMap, signalList, slotMap, slotList);
        //拿到每个类方法上的注解
//        getMethodAnnoOnClass(signalMap, signalList, slotMap, slotList);
        
      
    }
    
//    private void getMethodAnnoOnClass(Map<String, Object> signalMap, String[] signalList, Map<String, Object> slotMap, String[] slotList) {
//        Method[] signalMethods = new Method[1024];
//        int i = 0;
//        for (String s : signalList) {
//            Object o = signalMap.get(s);
//            Method[] declaredMethods = o.getClass().getDeclaredMethods();
//
//            for (Method declaredMethod : declaredMethods) {
//                signalMethods[i] = declaredMethod;
//                SignalMethod signalMethod = declaredMethod.getAnnotation(SignalMethod.class);
//                i++;
//            }
//        }
//
//        for (String s : slotList) {
//            //Slot 注解的类
//            Object o = slotMap.get(s);
//            //Connect 注解的方法
//            Method[] declaredMethods = o.getClass().getDeclaredMethods();
//            for (Method declaredMethod : declaredMethods) {
//                //TODO 注解中 的方法和类名匹配上之后 保存对应的关系
//                Connect connect = declaredMethod.getAnnotation(Connect.class);
//                //Signal类名
//                Class signalClazz = connect.clazz();
//                // signal 方法名
//                String[] signal = connect.signal().split(",");
//                for (String s1 : signal) {
//                    for (Method signalMethod : signalMethods) {
//                        if (signalMethod != null && signalMethod != null) {
//                            if (s1.equals(signalMethod.getName())) {
//                                SignalSlotProperties signalSlotProperties = new SignalSlotProperties();
//                                signalSlotProperties.setSignalClass(signalClazz);
//                                signalSlotProperties.setSignalMethod(s1);
//                                signalSlotProperties.setSlotClass(o.getClass());
//                                signalSlotProperties.setSlotMethod(declaredMethod.getName());
//                               SignalSlotConstant.signalSlotProperties.add(signalSlotProperties);
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    
    /**
     * 给全局变量赋值
     *
     * @param signalMap
     * @param signalList
     * @param slotMap
     * @param slotList
     */
    private void setConstantValue(Map<String, Object> signalMap, String[] signalList, Map<String, Object> slotMap, String[] slotList) {
        SignalSlotConstant.signalMap = signalMap;
        SignalSlotConstant.signalList = signalList;
        SignalSlotConstant.slotMap = slotMap;
        SignalSlotConstant.slotList = slotList;
    }
}
