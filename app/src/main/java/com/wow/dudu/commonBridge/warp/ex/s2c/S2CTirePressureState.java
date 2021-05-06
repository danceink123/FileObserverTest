package com.wow.dudu.commonBridge.warp.ex.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureState */
public class S2CTirePressureState extends BaseWarp {
    private boolean ready;

    public S2CTirePressureState setReady(boolean z) {
        this.ready = z;
        return this;
    }

    public boolean isReady() {
        return this.ready;
    }

    public S2CTirePressureState() {
        super(ExCmd.S2C_TIRE_PRESSURE_STATE);
    }
}
