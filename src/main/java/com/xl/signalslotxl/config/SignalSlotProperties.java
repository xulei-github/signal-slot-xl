package com.xl.signalslotxl.config;

import lombok.Data;

/**
 * @author xulei
 * @version 1.0
 * @date 2021/11/17 10:10
 */
@Data
public class SignalSlotProperties {
    private Class signalClass;
    private Class slotClass;
    private String signalMethod;
    private String slotMethod;
}
