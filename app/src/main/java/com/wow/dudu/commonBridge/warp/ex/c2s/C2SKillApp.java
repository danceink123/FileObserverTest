package com.wow.dudu.commonBridge.warp.ex.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.c2s.C2SKillApp */
public class C2SKillApp extends BaseWarp {
    private String clazz;

    public C2SKillApp setClazz(String str) {
        this.clazz = str;
        return this;
    }

    public C2SKillApp() {
        super(ExCmd.C2S_KILL_APP);
    }

    public String getClazz() {
        return this.clazz;
    }
}
