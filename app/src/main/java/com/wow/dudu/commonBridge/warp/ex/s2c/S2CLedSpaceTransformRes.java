package com.wow.dudu.commonBridge.warp.ex.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.s2c.S2CLedSpaceTransformRes */
public class S2CLedSpaceTransformRes extends BaseWarp {
    private byte[] message;

    public S2CLedSpaceTransformRes setMessage(byte[] bArr) {
        this.message = bArr;
        return this;
    }

    public S2CLedSpaceTransformRes() {
        super(ExCmd.S2C_LED_SPACE_TRANSFORM_RES);
    }

    public byte[] getMessage() {
        return this.message;
    }
}
