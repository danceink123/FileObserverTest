package com.wow.dudu.commonBridge.warp.carinfo.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.carinfo.CarInfoCmd;

public class S2CCarType extends BaseWarp {
    private Integer type;

    public S2CCarType setType(Integer num) {
        this.type = num;
        return this;
    }

    public Integer getType() {
        return this.type;
    }

    public S2CCarType() {
        super(CarInfoCmd.S2C_CAR_TYPE);
    }
}
