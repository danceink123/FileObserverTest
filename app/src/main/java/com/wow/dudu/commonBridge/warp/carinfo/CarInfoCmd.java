package com.wow.dudu.commonBridge.warp.carinfo;

public interface CarInfoCmd {
    public static final short C2S_CHECK_RES = 100;
    public static final short S2C_CAR_INFO = 200;
    public static final short S2C_CAR_KEY = 205;
    public static final short S2C_CAR_TP = 201;
    public static final short S2C_CAR_TYPE = 204;
    public static final short S2C_STATE = 202;

    public interface CarType {
        public static final int A800V = 102;
        public static final int AC822X = 104;
        public static final int MX6DQ = 100;
        public static final int ZOTYE_M12 = 103;
    }
}
