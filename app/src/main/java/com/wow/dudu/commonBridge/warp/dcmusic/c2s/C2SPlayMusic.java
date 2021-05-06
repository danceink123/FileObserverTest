package com.wow.dudu.commonBridge.warp.dcmusic.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class C2SPlayMusic extends BaseWarp {
    private int index;
    private String title;
    private String zuozhe;

    public C2SPlayMusic setIndex(int i) {
        this.index = i;
        return this;
    }

    public C2SPlayMusic setTitle(String str) {
        this.title = str;
        return this;
    }

    public C2SPlayMusic setZuozhe(String str) {
        this.zuozhe = str;
        return this;
    }

    public int getIndex() {
        return this.index;
    }

    public String getTitle() {
        return this.title;
    }

    public String getZuozhe() {
        return this.zuozhe;
    }

    public C2SPlayMusic() {
        super(DcmusicCmd.C2S_MUSIC_PLAY);
    }
}
