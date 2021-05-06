package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CDriverCmd extends BaseWarp {
    private int driverCmd;

    public S2CDriverCmd setDriverCmd(int i) {
        this.driverCmd = i;
        return this;
    }

    public int getDriverCmd() {
        return this.driverCmd;
    }

    public S2CDriverCmd() {
        super(DriverCmd.S2C_DRIVER_CMD);
    }
}
