package com.wow.dudu.commonBridge.warp.ex.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.s2c.S2CGetMusicLrcLoaded */
public class S2CGetMusicLrcLoaded extends BaseWarp {
    private String key;
    private String lrc;

    public S2CGetMusicLrcLoaded setKey(String str) {
        this.key = str;
        return this;
    }

    public S2CGetMusicLrcLoaded setLrc(String str) {
        this.lrc = str;
        return this;
    }

    public S2CGetMusicLrcLoaded() {
        super(ExCmd.S2C_GET_MUSIC_LRC_LOADED);
    }

    public String getKey() {
        return this.key;
    }

    public String getLrc() {
        return this.lrc;
    }
}
