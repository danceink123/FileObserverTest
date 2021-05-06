package com.wow.dudu.commonBridge.warp.driver.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class C2SDriverCmd extends BaseWarp {
    private int driverCmd;

    public C2SDriverCmd setDriverCmd(int i) {
        this.driverCmd = i;
        return this;
    }

    public int getDriverCmd() {
        return this.driverCmd;
    }

    public C2SDriverCmd() {
        super(DriverCmd.C2S_DRIVER_CMD);
    }
}
