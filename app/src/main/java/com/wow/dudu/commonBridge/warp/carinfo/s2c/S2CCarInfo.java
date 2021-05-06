package com.wow.dudu.commonBridge.warp.carinfo.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.carinfo.CarInfoCmd;

public class S2CCarInfo extends BaseWarp {
    private Integer oilConsumption;
    private Integer rev;
    private Integer speed;
    private Double voltage;
    private Integer waterTemp;

    public S2CCarInfo setOilConsumption(Integer num) {
        this.oilConsumption = num;
        return this;
    }

    public S2CCarInfo setRev(Integer num) {
        this.rev = num;
        return this;
    }

    public S2CCarInfo setSpeed(Integer num) {
        this.speed = num;
        return this;
    }

    public S2CCarInfo setVoltage(Double d) {
        this.voltage = d;
        return this;
    }

    public S2CCarInfo setWaterTemp(Integer num) {
        this.waterTemp = num;
        return this;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public Integer getRev() {
        return this.rev;
    }

    public Integer getWaterTemp() {
        return this.waterTemp;
    }

    public Integer getOilConsumption() {
        return this.oilConsumption;
    }

    public Double getVoltage() {
        return this.voltage;
    }

    public S2CCarInfo() {
        super(CarInfoCmd.S2C_CAR_INFO);
    }
}
