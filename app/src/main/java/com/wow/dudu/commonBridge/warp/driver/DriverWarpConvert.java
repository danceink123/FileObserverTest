package com.wow.dudu.commonBridge.warp.driver;

import com.wow.dudu.commonBridge.Opcodes;
import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.FromJsonInterface;
import com.wow.dudu.commonBridge.warp.driver.c2s.C2SDriverCmd;
import com.wow.dudu.commonBridge.warp.driver.c2s.C2SGetTripTime;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CCarInfo;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CCarTp;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CDriverCmd;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CGetTripTimeRes;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CLocationInfo;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CMusicCover;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CMusicInfo;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CMusicState;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CNavInfo;
import com.wow.dudu.commonBridge.warp.driver.s2c.S2CWeatherInfo;

public class DriverWarpConvert {
    public static BaseWarp decodeJson(short s, String str, FromJsonInterface fromJsonInterface) {
        if (s == 100) {
            return fromJsonInterface.fromJson(str, C2SDriverCmd.class);
        }
        if (s == 101) {
            return fromJsonInterface.fromJson(str, C2SGetTripTime.class);
        }
        if (s == 200) {
            return fromJsonInterface.fromJson(str, S2CDriverCmd.class);
        }
        if (s == 260) {
            return fromJsonInterface.fromJson(str, S2CGetTripTimeRes.class);
        }
        switch (s) {
            case Opcodes.ADD_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CCarInfo.class);
            case Opcodes.SUB_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CCarTp.class);
            case Opcodes.MUL_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CNavInfo.class);
            case Opcodes.DIV_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CLocationInfo.class);
            case Opcodes.REM_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CMusicState.class);
            case Opcodes.ADD_INT_LIT16:
                return fromJsonInterface.fromJson(str, S2CMusicInfo.class);
            case Opcodes.RSUB_INT:
                return fromJsonInterface.fromJson(str, S2CMusicCover.class);
            case Opcodes.MUL_INT_LIT16:
                return fromJsonInterface.fromJson(str, S2CWeatherInfo.class);
            default:
                return null;
        }
    }
}
