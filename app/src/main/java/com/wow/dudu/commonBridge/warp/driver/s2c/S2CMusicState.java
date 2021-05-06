package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CMusicState extends BaseWarp {
    private boolean state;

    public S2CMusicState setState(boolean z) {
        this.state = z;
        return this;
    }

    public boolean isState() {
        return this.state;
    }

    public S2CMusicState() {
        super(DriverCmd.S2C_MUSIC_STATE);
    }
}
