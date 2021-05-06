package com.wow.dudu.commonBridge.warp.dcmusic.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class S2CMusicLrc extends BaseWarp {
    private String lrc;
    private String title;
    private String zuozhe;

    public S2CMusicLrc setLrc(String str) {
        this.lrc = str;
        return this;
    }

    public S2CMusicLrc setTitle(String str) {
        this.title = str;
        return this;
    }

    public S2CMusicLrc setZuozhe(String str) {
        this.zuozhe = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public String getZuozhe() {
        return this.zuozhe;
    }

    public String getLrc() {
        return this.lrc;
    }

    public S2CMusicLrc() {
        super(DcmusicCmd.S2C_MUSIC_LRC);
    }
}
