package com.wow.dudu.commonBridge.warp.ex.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.c2s.C2SLedSpaceTranslate */
public class C2SLedSpaceTranslate extends BaseWarp {
    private byte[] message;

    public C2SLedSpaceTranslate setMessage(byte[] bArr) {
        this.message = bArr;
        return this;
    }

    public C2SLedSpaceTranslate() {
        super(ExCmd.C2S_LED_SPACE_TRANSLATE);
    }

    public byte[] getMessage() {
        return this.message;
    }
}
