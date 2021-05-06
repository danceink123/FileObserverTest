package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CLocationInfo extends BaseWarp {
    private double altitude = -1.0d;
    private float bearing;
    private int speed;

    public S2CLocationInfo setAltitude(double d) {
        this.altitude = d;
        return this;
    }

    public S2CLocationInfo setBearing(float f) {
        this.bearing = f;
        return this;
    }

    public S2CLocationInfo setSpeed(int i) {
        this.speed = i;
        return this;
    }

    public int getSpeed() {
        return this.speed;
    }

    public float getBearing() {
        return this.bearing;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public S2CLocationInfo() {
        super(DriverCmd.S2C_CAR_SPEED);
    }
}
