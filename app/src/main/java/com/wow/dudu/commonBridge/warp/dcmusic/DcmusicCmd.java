package com.wow.dudu.commonBridge.warp.dcmusic;

public interface DcmusicCmd {
    public static final short C2S_MUSIC_CMD = 100;
    public static final short C2S_MUSIC_LIST = 101;
    public static final short C2S_MUSIC_PLAY = 102;
    public static final short RES_MUSIC_LIST = 106;
    public static final short S2C_MUSIC_COVER = 202;
    public static final short S2C_MUSIC_INFO = 201;
    public static final short S2C_MUSIC_LRC = 203;
    public static final short S2C_MUSIC_PROGRESS = 204;
    public static final short S2C_MUSIC_STATE = 200;
    public static final short S2C_MUSIC_WAVE_FORM_DATA = 205;

    public interface MusicCmd {
        public static final short NEXT = 101;
        public static final short PAUSE = 103;
        public static final short PLAY = 102;
        public static final short PRE = 100;
    }
}
