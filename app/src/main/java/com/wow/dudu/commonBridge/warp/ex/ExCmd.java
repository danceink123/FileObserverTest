package com.wow.dudu.commonBridge.warp.ex;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.ExCmd */
public interface ExCmd {
    public static final short C2S_EX_CMD = 103;
    public static final short C2S_GET_MUSIC_LRC_REQ = 102;
    public static final short C2S_KILL_APP = 104;
    public static final short C2S_LED_SPACE_TRANSFORM = 100;
    public static final short C2S_LED_SPACE_TRANSLATE = 101;
    public static final short S2C_GET_MUSIC_LRC_LOADED = 204;
    public static final short S2C_LED_SPACE_TRANSFORM_RES = 200;
    public static final short S2C_LED_SPACE_TRANSLATE_RES = 201;
    public static final short S2C_TIRE_PRESSURE_INFO = 202;
    public static final short S2C_TIRE_PRESSURE_STATE = 203;

    /* renamed from: com.wow.dudu.commonBridge.warp.ex.ExCmd$C2SCmd */
    public interface C2SCmd {
        public static final int REOPEN_AP = 101;
        public static final int REREQUEST_CONNECT_TY = 100;
    }
}
