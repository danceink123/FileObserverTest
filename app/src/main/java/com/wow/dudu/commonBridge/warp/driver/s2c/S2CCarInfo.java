package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CCarInfo extends BaseWarp {
    private boolean connect = false;
    private int oil;
    private int rev;
    private int speed;
    private int temp;
    private double voltage;

    public S2CCarInfo setConnect(boolean z) {
        this.connect = z;
        return this;
    }

    public S2CCarInfo setOil(int i) {
        this.oil = i;
        return this;
    }

    public S2CCarInfo setRev(int i) {
        this.rev = i;
        return this;
    }

    public S2CCarInfo setSpeed(int i) {
        this.speed = i;
        return this;
    }

    public S2CCarInfo setTemp(int i) {
        this.temp = i;
        return this;
    }

    public S2CCarInfo setVoltage(double d) {
        this.voltage = d;
        return this;
    }

    public boolean isConnect() {
        return this.connect;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getRev() {
        return this.rev;
    }

    public int getOil() {
        return this.oil;
    }

    public int getTemp() {
        return this.temp;
    }

    public double getVoltage() {
        return this.voltage;
    }

    public S2CCarInfo() {
        super(DriverCmd.S2C_CAR_INFO);
    }
}
