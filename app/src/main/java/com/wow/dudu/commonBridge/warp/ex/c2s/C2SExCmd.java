package com.wow.dudu.commonBridge.warp.ex.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.c2s.C2SExCmd */
public class C2SExCmd extends BaseWarp {
    private int excmd;

    public C2SExCmd setExcmd(int i) {
        this.excmd = i;
        return this;
    }

    public int getExcmd() {
        return this.excmd;
    }

    public C2SExCmd() {
        super(ExCmd.C2S_EX_CMD);
    }
}
