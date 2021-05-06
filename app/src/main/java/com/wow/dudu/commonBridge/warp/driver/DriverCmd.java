package com.wow.dudu.commonBridge.warp.driver;

public interface DriverCmd {
    public static final short C2S_DRIVER_CMD = 100;
    public static final short C2S_GET_TRIP_TIME = 101;
    public static final short S2C_CAMERA_INFO = 211;
    public static final short S2C_CAR_INFO = 203;
    public static final short S2C_CAR_SPEED = 206;
    public static final short S2C_CAR_TP = 204;
    public static final short S2C_DRIVER_CMD = 200;
    public static final short S2C_GET_TRIP_TIME_RES = 260;
    public static final short S2C_MUSIC_COVER = 209;
    public static final short S2C_MUSIC_INFO = 208;
    public static final short S2C_MUSIC_STATE = 207;
    public static final short S2C_NAV_INFO = 205;
    public static final short S2C_TRIP_INFO = 212;
    public static final short S2C_WEATHER_INFO = 210;

    public interface C2SCmd {
        public static final int DRIVING_HIDE = 104;
        public static final int DRIVING_SHOW = 103;
        public static final int NEXT = 101;
        public static final int PLAY_OR_PAUSE = 102;
        public static final int PRE = 100;
    }

    public interface S2CCmd {
        public static final int CHANGE_NAV = 101;
        public static final int MOVE_TO_BACK = 100;
    }
}
