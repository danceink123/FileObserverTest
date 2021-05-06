package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CCameraInfo extends BaseWarp {
    private int cameraDist = -1;
    private int cameraSpeed;

    public S2CCameraInfo setCameraDist(int i) {
        this.cameraDist = i;
        return this;
    }

    public S2CCameraInfo setCameraSpeed(int i) {
        this.cameraSpeed = i;
        return this;
    }

    public int getCameraSpeed() {
        return this.cameraSpeed;
    }

    public int getCameraDist() {
        return this.cameraDist;
    }

    public S2CCameraInfo() {
        super(DriverCmd.S2C_CAMERA_INFO);
    }
}
