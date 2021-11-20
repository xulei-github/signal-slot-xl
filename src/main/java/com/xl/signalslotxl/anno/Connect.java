package com.xl.signalslotxl.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/16 17:55
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Connect {
    Class clazz();
    
    String signal() default "";
}
