package com.wow.dudu.commonBridge.warp.ex.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.c2s.C2SLedSpaceTransform */
public class C2SLedSpaceTransform extends BaseWarp {
    private String message;

    public C2SLedSpaceTransform setMessage(String str) {
        this.message = str;
        return this;
    }

    public C2SLedSpaceTransform() {
        super(ExCmd.C2S_LED_SPACE_TRANSFORM);
    }

    public String getMessage() {
        return this.message;
    }
}
