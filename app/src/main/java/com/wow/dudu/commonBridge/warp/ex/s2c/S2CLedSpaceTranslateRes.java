package com.wow.dudu.commonBridge.warp.ex.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.s2c.S2CLedSpaceTranslateRes */
public class S2CLedSpaceTranslateRes extends BaseWarp {
    private String message;

    public S2CLedSpaceTranslateRes setMessage(String str) {
        this.message = str;
        return this;
    }

    public S2CLedSpaceTranslateRes() {
        super(ExCmd.S2C_LED_SPACE_TRANSLATE_RES);
    }

    public String getMessage() {
        return this.message;
    }
}
