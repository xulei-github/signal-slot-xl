package com.xl.signalslotxl.aspect;

import com.xl.signalslotxl.config.SignalSlotProperties;
import com.xl.signalslotxl.constant.SignalSlotConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/17 9:11
 */
@Aspect
@Component
public class SignalAspect {
    @Pointcut("@annotation(com.xl.signalslotxl.anno.SignalMethod)")
    public void signalPoinCut() {
    }
    
    @After(value = "signalPoinCut()")
    public void afterSignalPointCut(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        Object[] args = joinPoint.getArgs();
//        String declaringTypeName = signature.getDeclaringTypeName();
//        String s = Modifier.toString(signature.getModifiers());
        Class declaringType = signature.getDeclaringType();
        for (SignalSlotProperties signalSlotProperty : SignalSlotConstant.signalSlotProperties) {
            if (signalSlotProperty.getSignalClass().getName().equals(declaringType.getName())) {
                if (signalSlotProperty.getSignalMethod().equals(name)) {
//                    匹配成功
                    
                    createEntity(signalSlotProperty, args);
                    
                }
            }
        }
    }
    
    private void createEntity(SignalSlotProperties signalSlotProperty, Object[] args) {
        Class slotClass = signalSlotProperty.getSlotClass();
        Object o = null;
        try {
            o = slotClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(signalSlotProperty.getSlotMethod())) {
                Object finalO = o;
                SignalSlotConstant.executor.execute(() -> {
                    try {
                        method.invoke(finalO, args);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        
    }
}
