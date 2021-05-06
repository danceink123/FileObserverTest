package com.wow.dudu.commonBridge.warp.ex.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.c2s.C2SGetMusicLrcReq */
public class C2SGetMusicLrcReq extends BaseWarp {
    private String key;
    private Integer time;

    public C2SGetMusicLrcReq setKey(String str) {
        this.key = str;
        return this;
    }

    public C2SGetMusicLrcReq setTime(Integer num) {
        this.time = num;
        return this;
    }

    public C2SGetMusicLrcReq() {
        super(ExCmd.C2S_GET_MUSIC_LRC_REQ);
    }

    public String getKey() {
        return this.key;
    }

    public Integer getTime() {
        return this.time;
    }
}
