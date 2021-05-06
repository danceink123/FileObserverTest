package com.wow.dudu.commonBridge.warp.carinfo.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.carinfo.CarInfoCmd;

public class S2CCarState extends BaseWarp {
    private boolean infoReady;
    private boolean tirePressureReady;

    public S2CCarState setInfoReady(boolean z) {
        this.infoReady = z;
        return this;
    }

    public S2CCarState setTirePressureReady(boolean z) {
        this.tirePressureReady = z;
        return this;
    }

    public boolean isInfoReady() {
        return this.infoReady;
    }

    public boolean isTirePressureReady() {
        return this.tirePressureReady;
    }

    public S2CCarState() {
        super(CarInfoCmd.S2C_STATE);
    }
}
