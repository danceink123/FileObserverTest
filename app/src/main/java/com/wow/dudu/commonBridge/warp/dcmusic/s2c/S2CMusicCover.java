package com.wow.dudu.commonBridge.warp.dcmusic.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class S2CMusicCover extends BaseWarp {
    private String msg;
    private String title;
    private String zuozhe;

    public S2CMusicCover setMsg(String str) {
        this.msg = str;
        return this;
    }

    public S2CMusicCover setTitle(String str) {
        this.title = str;
        return this;
    }

    public S2CMusicCover setZuozhe(String str) {
        this.zuozhe = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public String getZuozhe() {
        return this.zuozhe;
    }

    public String getMsg() {
        return this.msg;
    }

    public S2CMusicCover() {
        super(DcmusicCmd.S2C_MUSIC_COVER);
    }
}
