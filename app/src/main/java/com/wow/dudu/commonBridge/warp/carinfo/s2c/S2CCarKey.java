package com.wow.dudu.commonBridge.warp.carinfo.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.carinfo.CarInfoCmd;

public class S2CCarKey extends BaseWarp {
    private int key;
    private Integer type;

    public S2CCarKey setKey(int i) {
        this.key = i;
        return this;
    }

    public S2CCarKey setType(Integer num) {
        this.type = num;
        return this;
    }

    public Integer getType() {
        return this.type;
    }

    public int getKey() {
        return this.key;
    }

    public S2CCarKey() {
        super(CarInfoCmd.S2C_CAR_KEY);
    }
}
