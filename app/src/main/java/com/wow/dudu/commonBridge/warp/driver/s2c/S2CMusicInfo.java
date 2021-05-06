package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CMusicInfo extends BaseWarp {
    private String title;
    private String zuozhe;

    public S2CMusicInfo setTitle(String str) {
        this.title = str;
        return this;
    }

    public S2CMusicInfo setZuozhe(String str) {
        this.zuozhe = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public String getZuozhe() {
        return this.zuozhe;
    }

    public S2CMusicInfo() {
        super(DriverCmd.S2C_MUSIC_INFO);
    }
}
