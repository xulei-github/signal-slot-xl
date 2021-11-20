package com.xl.signalslotxl.constant;

import com.xl.signalslotxl.config.SignalSlotProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 把当前的类作为全局共享类
 *
 * @author xulei
 * @version 1.0
 * @date 2021/11/16 19:29
 */
@Data
public class SignalSlotConstant {
    /*信号的类名和类*/
    public static Map<String, Object> signalMap = null;
    /*被信号注解标记的类名*/
    public static String[] signalList = null;
    
    /*槽的类名和类*/
    public static Map<String, Object> slotMap = null;
    /*被槽注解标记的类名*/
    public static String[] slotList = null;
    
    //信号的方法 和槽的方法 对应关系
    public static Map<String, String> signalSlotMethodMap = new HashMap<>();
    public static List<SignalSlotProperties> signalSlotProperties = new ArrayList<>();
    
    public static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(64);
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 10, TimeUnit.MILLISECONDS, arrayBlockingQueue);
}
