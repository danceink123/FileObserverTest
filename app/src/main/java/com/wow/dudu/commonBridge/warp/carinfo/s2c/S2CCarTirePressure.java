package com.wow.dudu.commonBridge.warp.carinfo.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.carinfo.CarInfoCmd;

public class S2CCarTirePressure extends BaseWarp {
    private Integer lBTemp;
    private Float lBTirePressure;
    private Integer lFTemp;
    private Float lFTirePressure;
    private Integer rBTemp;
    private Float rBTirePressure;
    private Integer rFTemp;
    private Float rFTirePressure;

    public S2CCarTirePressure setLBTemp(Integer num) {
        this.lBTemp = num;
        return this;
    }

    public S2CCarTirePressure setLBTirePressure(Float f) {
        this.lBTirePressure = f;
        return this;
    }

    public S2CCarTirePressure setLFTemp(Integer num) {
        this.lFTemp = num;
        return this;
    }

    public S2CCarTirePressure setLFTirePressure(Float f) {
        this.lFTirePressure = f;
        return this;
    }

    public S2CCarTirePressure setRBTemp(Integer num) {
        this.rBTemp = num;
        return this;
    }

    public S2CCarTirePressure setRBTirePressure(Float f) {
        this.rBTirePressure = f;
        return this;
    }

    public S2CCarTirePressure setRFTemp(Integer num) {
        this.rFTemp = num;
        return this;
    }

    public S2CCarTirePressure setRFTirePressure(Float f) {
        this.rFTirePressure = f;
        return this;
    }

    public Float getLFTirePressure() {
        return this.lFTirePressure;
    }

    public Integer getLFTemp() {
        return this.lFTemp;
    }

    public Float getRFTirePressure() {
        return this.rFTirePressure;
    }

    public Integer getRFTemp() {
        return this.rFTemp;
    }

    public Float getLBTirePressure() {
        return this.lBTirePressure;
    }

    public Integer getLBTemp() {
        return this.lBTemp;
    }

    public Float getRBTirePressure() {
        return this.rBTirePressure;
    }

    public Integer getRBTemp() {
        return this.rBTemp;
    }

    public S2CCarTirePressure() {
        super(CarInfoCmd.S2C_CAR_TP);
    }
}
