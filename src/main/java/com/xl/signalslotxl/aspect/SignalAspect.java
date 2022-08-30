package com.xl.signalslotxl.aspect;

import com.xl.signalslotxl.anno.Connect;
import com.xl.signalslotxl.anno.SignalMethod;
import com.xl.signalslotxl.config.SignalSlotProperties;
import com.xl.signalslotxl.constant.SignalSlotConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Before("signalPoinCut()")
    public void BeforeSingnalPointCut(JoinPoint joinPoint) {
        getMethodAnnoOnClass(joinPoint, SignalSlotConstant.slotMap, SignalSlotConstant.slotList);
    }

    @After(value = "signalPoinCut()")
    public void afterSignalPointCut(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        Object[] args = joinPoint.getArgs();
//        String declaringTypeName = signature.getDeclaringTypeName();
//        String s = Modifier.toString(signature.getModifiers());
        Class declaringType = signature.getDeclaringType();

        Method method = ((MethodSignature) signature).getMethod();
        SignalMethod annotation = method.getAnnotation(SignalMethod.class);
        for (SignalSlotProperties signalSlotProperty : SignalSlotConstant.signalSlotProperties) {
            if (signalSlotProperty.getSignalClass().getName().equals(declaringType.getName())) {
                if (signalSlotProperty.getSignalMethod().equals(name)) {
//                    匹配成功
                    createEntity(signalSlotProperty, args);

                }
            }
        }
    }

    /**
     * 创建实体类并调用
     * @param signalSlotProperty
     * @param args
     */
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

    /**
     * 获取singalMethod注解,并把对应关系加载到缓存里
     * @param joinPoint
     * @param slotMap
     * @param slotList
     */
    private void getMethodAnnoOnClass(JoinPoint joinPoint, Map<String, Object> slotMap, String[] slotList) {
        //拿到当前调用接口的信息
        Signature signature = joinPoint.getSignature();
        Class<?> aClass = joinPoint.getThis().getClass();
        //被signalMethod标记方法的类名
        String signalMethodClassName = aClass.getName().substring(0, aClass.getName().indexOf("$"));
        //被signalMethod标记的方法
        MethodSignature signalMethods = (MethodSignature) signature;
        Method method = signalMethods.getMethod();
        //获取当前注解上的methodName参数
        SignalMethod annotation = method.getAnnotation(SignalMethod.class);
        String signalMethodName = annotation.methodName();
        if (StringUtils.isEmpty(signalMethodName)) {
            signalMethodName = method.getName();
        }

        //获取所有和当前注解有关系的connect
        for (String s : slotList) {
            //Slot 注解的类
            Object soltClazz = slotMap.get(s);
            //Connect 注解的方法
            Method[] declaredMethods = soltClazz.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                //TODO 注解中 的方法和类名匹配上之后 保存对应的关系
                Connect connect = declaredMethod.getAnnotation(Connect.class);
                if (connect != null) {
                    String conSignal = connect.signal();
                    Class conClazzClass = connect.clazz();
                    String conClazzName = conClazzClass.getName();
                    if (conSignal.contains(signalMethodName) && conClazzName.equals(signalMethodClassName)) {
                        // signal 方法名
                        String methodName = method.getName();

                        SignalSlotProperties signalSlotProperties = new SignalSlotProperties();
                        signalSlotProperties.setSignalClass(conClazzClass);
                        signalSlotProperties.setSignalMethod(methodName);
                        signalSlotProperties.setSlotClass(soltClazz.getClass());
                        signalSlotProperties.setSlotMethod(declaredMethod.getName());

                        //通过重写 hashCode和equals实现去重
                        SignalSlotConstant.signalSlotProperties.add(signalSlotProperties);
                    }
                }
            }

        }
    }


}
