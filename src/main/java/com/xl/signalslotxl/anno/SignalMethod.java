package com.xl.signalslotxl.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/16 20:46
 */@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SignalMethod {
}
