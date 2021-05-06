package com.wow.dudu.commonBridge.warp.dcmusic.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class S2CMusicInfo extends BaseWarp {
    private String title;
    private int total;
    private String zuozhe;

    public S2CMusicInfo setTitle(String str) {
        this.title = str;
        return this;
    }

    public S2CMusicInfo setTotal(int i) {
        this.total = i;
        return this;
    }

    public S2CMusicInfo setZuozhe(String str) {
        this.zuozhe = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public String getZuozhe() {
        return this.zuozhe;
    }

    public int getTotal() {
        return this.total;
    }

    public S2CMusicInfo() {
        super(DcmusicCmd.S2C_MUSIC_INFO);
    }
}
