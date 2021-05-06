package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CTripInfo extends BaseWarp {
    private int averageSpeed = 0;
    private int distance = 0;
    private int maxSpeed = 0;
    private long tripStartTime = 0;
    private boolean triping = false;

    public S2CTripInfo setAverageSpeed(int i) {
        this.averageSpeed = i;
        return this;
    }

    public S2CTripInfo setDistance(int i) {
        this.distance = i;
        return this;
    }

    public S2CTripInfo setMaxSpeed(int i) {
        this.maxSpeed = i;
        return this;
    }

    public S2CTripInfo setTripStartTime(long j) {
        this.tripStartTime = j;
        return this;
    }

    public S2CTripInfo setTriping(boolean z) {
        this.triping = z;
        return this;
    }

    public boolean isTriping() {
        return this.triping;
    }

    public long getTripStartTime() {
        return this.tripStartTime;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public int getAverageSpeed() {
        return this.averageSpeed;
    }

    public S2CTripInfo() {
        super(DriverCmd.S2C_TRIP_INFO);
    }
}
