package com.wow.dudu.commonBridge.warp.ex;

import com.wow.dudu.commonBridge.Opcodes;
import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.FromJsonInterface;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SExCmd;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SGetMusicLrcReq;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SKillApp;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SLedSpaceTransform;
import com.wow.dudu.commonBridge.warp.ex.c2s.C2SLedSpaceTranslate;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CGetMusicLrcLoaded;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CLedSpaceTransformRes;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CLedSpaceTranslateRes;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureInfo;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureState;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.ExWarpConvert */
public class ExWarpConvert {
    public static BaseWarp decodeJson(short s, String str, FromJsonInterface fromJsonInterface) {
        switch (s) {
            case 100:
                return fromJsonInterface.fromJson(str, C2SLedSpaceTransform.class);
            case 101:
                return fromJsonInterface.fromJson(str, C2SLedSpaceTranslate.class);
            case 102:
                return fromJsonInterface.fromJson(str, C2SGetMusicLrcReq.class);
            case 103:
                return fromJsonInterface.fromJson(str, C2SExCmd.class);
            case 104:
                return fromJsonInterface.fromJson(str, C2SKillApp.class);
            default:
                switch (s) {
                    case 200:
                        return fromJsonInterface.fromJson(str, S2CLedSpaceTransformRes.class);
                    case 201:
                        return fromJsonInterface.fromJson(str, S2CLedSpaceTranslateRes.class);
                    case Opcodes.REM_FLOAT_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CTirePressureInfo.class);
                    case Opcodes.ADD_DOUBLE_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CTirePressureState.class);
                    case Opcodes.SUB_DOUBLE_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CGetMusicLrcLoaded.class);
                    default:
                        return null;
                }
        }
    }
}
