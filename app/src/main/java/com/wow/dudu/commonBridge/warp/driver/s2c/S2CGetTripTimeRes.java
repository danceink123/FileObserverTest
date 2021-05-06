package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CGetTripTimeRes extends BaseWarp {
    private long time;

    public S2CGetTripTimeRes setTime(long j) {
        this.time = j;
        return this;
    }

    public long getTime() {
        return this.time;
    }

    public S2CGetTripTimeRes() {
        super(DriverCmd.S2C_GET_TRIP_TIME_RES);
    }
}
