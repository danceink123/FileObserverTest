package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CMusicCover extends BaseWarp {
    private String msg;
    private int type;

    public S2CMusicCover setMsg(String str) {
        this.msg = str;
        return this;
    }

    public S2CMusicCover setType(int i) {
        this.type = i;
        return this;
    }

    public int getType() {
        return this.type;
    }

    public String getMsg() {
        return this.msg;
    }

    public S2CMusicCover() {
        super(DriverCmd.S2C_MUSIC_COVER);
    }
}
