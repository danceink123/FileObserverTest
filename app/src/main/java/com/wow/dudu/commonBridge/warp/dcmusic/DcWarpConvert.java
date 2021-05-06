package com.wow.dudu.commonBridge.warp.dcmusic;

import com.wow.dudu.commonBridge.Opcodes;
import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.FromJsonInterface;
import com.wow.dudu.commonBridge.warp.dcmusic.c2s.C2SGetMusicList;
import com.wow.dudu.commonBridge.warp.dcmusic.c2s.C2SMusicCmd;
import com.wow.dudu.commonBridge.warp.dcmusic.c2s.C2SPlayMusic;
import com.wow.dudu.commonBridge.warp.dcmusic.c2s.ResMusicList;
import com.wow.dudu.commonBridge.warp.dcmusic.s2c.S2CMusicCover;
import com.wow.dudu.commonBridge.warp.dcmusic.s2c.S2CMusicInfo;
import com.wow.dudu.commonBridge.warp.dcmusic.s2c.S2CMusicLrc;
import com.wow.dudu.commonBridge.warp.dcmusic.s2c.S2CMusicProgress;
import com.wow.dudu.commonBridge.warp.dcmusic.s2c.S2CMusicState;
import com.wow.dudu.commonBridge.warp.dcmusic.s2c.S2CMusicWaveFormData;

public class DcWarpConvert {
    public static BaseWarp decodeJson(short s, String str, FromJsonInterface fromJsonInterface) {
        if (s == 106) {
            return fromJsonInterface.fromJson(str, ResMusicList.class);
        }
        switch (s) {
            case 100:
                return fromJsonInterface.fromJson(str, C2SMusicCmd.class);
            case 101:
                return fromJsonInterface.fromJson(str, C2SGetMusicList.class);
            case 102:
                return fromJsonInterface.fromJson(str, C2SPlayMusic.class);
            default:
                switch (s) {
                    case 200:
                        return fromJsonInterface.fromJson(str, S2CMusicState.class);
                    case 201:
                        return fromJsonInterface.fromJson(str, S2CMusicInfo.class);
                    case Opcodes.REM_FLOAT_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CMusicCover.class);
                    case Opcodes.ADD_DOUBLE_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CMusicLrc.class);
                    case Opcodes.SUB_DOUBLE_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CMusicProgress.class);
                    case Opcodes.MUL_DOUBLE_2ADDR:
                        return fromJsonInterface.fromJson(str, S2CMusicWaveFormData.class);
                    default:
                        return null;
                }
        }
    }
}
