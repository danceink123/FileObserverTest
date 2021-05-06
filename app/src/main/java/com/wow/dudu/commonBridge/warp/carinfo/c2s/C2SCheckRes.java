package com.wow.dudu.commonBridge.warp.carinfo.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.carinfo.CarInfoCmd;

public class C2SCheckRes extends BaseWarp {
    private boolean canuse;

    public C2SCheckRes setCanuse(boolean z) {
        this.canuse = z;
        return this;
    }

    public boolean isCanuse() {
        return this.canuse;
    }

    public C2SCheckRes() {
        super(CarInfoCmd.C2S_CHECK_RES);
    }
}
