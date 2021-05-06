package com.wow.dudu.commonBridge.warp.carinfo;

import com.wow.dudu.commonBridge.Opcodes;
import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.FromJsonInterface;
import com.wow.dudu.commonBridge.warp.carinfo.s2c.S2CCarInfo;
import com.wow.dudu.commonBridge.warp.carinfo.s2c.S2CCarKey;
import com.wow.dudu.commonBridge.warp.carinfo.s2c.S2CCarState;
import com.wow.dudu.commonBridge.warp.carinfo.s2c.S2CCarTirePressure;
import com.wow.dudu.commonBridge.warp.carinfo.s2c.S2CCarType;

public class CarInfoWarpConvert {
    public static BaseWarp decodeJson(short s, String str, FromJsonInterface fromJsonInterface) {
        switch (s) {
            case 200:
                return fromJsonInterface.fromJson(str, S2CCarInfo.class);
            case 201:
                return fromJsonInterface.fromJson(str, S2CCarTirePressure.class);
            case Opcodes.REM_FLOAT_2ADDR:
                return fromJsonInterface.fromJson(str, S2CCarState.class);
            case Opcodes.ADD_DOUBLE_2ADDR:
            default:
                return null;
            case Opcodes.SUB_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CCarType.class);
            case Opcodes.MUL_DOUBLE_2ADDR:
                return fromJsonInterface.fromJson(str, S2CCarKey.class);
        }
    }
}
