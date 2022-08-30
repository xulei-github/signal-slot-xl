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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SignalSlotProperties) {
            SignalSlotProperties signalSlotProperties = (SignalSlotProperties) obj;
            return signalClass.getName().equals(signalSlotProperties.getSignalClass().getName())
                    && signalMethod.equals(signalSlotProperties.getSignalMethod());
        }
        return false;
    }

    @Override
    public int hashCode() {
        String str = signalClass.getName() + signalMethod;
        int result = str.hashCode();
        return result;
    }
}
