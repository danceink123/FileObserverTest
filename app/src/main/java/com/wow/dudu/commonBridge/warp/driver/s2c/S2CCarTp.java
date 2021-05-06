package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CCarTp extends BaseWarp {
    private boolean connect = false;
    private float lbp = -1.0f;
    private int lbt = -1;
    private float lfp = -1.0f;
    private int lft = -1;
    private float rbp = -1.0f;
    private int rbt = -1;
    private float rfp = -1.0f;
    private int rft = -1;

    public S2CCarTp setConnect(boolean z) {
        this.connect = z;
        return this;
    }

    public S2CCarTp setLbp(float f) {
        this.lbp = f;
        return this;
    }

    public S2CCarTp setLbt(int i) {
        this.lbt = i;
        return this;
    }

    public S2CCarTp setLfp(float f) {
        this.lfp = f;
        return this;
    }

    public S2CCarTp setLft(int i) {
        this.lft = i;
        return this;
    }

    public S2CCarTp setRbp(float f) {
        this.rbp = f;
        return this;
    }

    public S2CCarTp setRbt(int i) {
        this.rbt = i;
        return this;
    }

    public S2CCarTp setRfp(float f) {
        this.rfp = f;
        return this;
    }

    public S2CCarTp setRft(int i) {
        this.rft = i;
        return this;
    }

    public boolean isConnect() {
        return this.connect;
    }

    public float getLfp() {
        return this.lfp;
    }

    public int getLft() {
        return this.lft;
    }

    public float getRfp() {
        return this.rfp;
    }

    public int getRft() {
        return this.rft;
    }

    public float getLbp() {
        return this.lbp;
    }

    public int getLbt() {
        return this.lbt;
    }

    public float getRbp() {
        return this.rbp;
    }

    public int getRbt() {
        return this.rbt;
    }

    public S2CCarTp() {
        super(DriverCmd.S2C_CAR_TP);
    }
}
