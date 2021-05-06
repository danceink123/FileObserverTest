package com.wow.dudu.commonBridge.warp.driver.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class C2SGetTripTime extends BaseWarp {
    public C2SGetTripTime() {
        super(DriverCmd.C2S_GET_TRIP_TIME);
    }
}
