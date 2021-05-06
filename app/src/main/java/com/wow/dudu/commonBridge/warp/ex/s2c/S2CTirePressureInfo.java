package com.wow.dudu.commonBridge.warp.ex.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.ex.ExCmd;

/* renamed from: com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureInfo */
public class S2CTirePressureInfo extends BaseWarp {
    private Integer lBTemp;
    private Float lBTirePressure;
    private Integer lFTemp;
    private Float lFTirePressure;
    private Integer rBTemp;
    private Float rBTirePressure;
    private Integer rFTemp;
    private Float rFTirePressure;

    public S2CTirePressureInfo setLBTemp(Integer num) {
        this.lBTemp = num;
        return this;
    }

    public S2CTirePressureInfo setLBTirePressure(Float f) {
        this.lBTirePressure = f;
        return this;
    }

    public S2CTirePressureInfo setLFTemp(Integer num) {
        this.lFTemp = num;
        return this;
    }

    public S2CTirePressureInfo setLFTirePressure(Float f) {
        this.lFTirePressure = f;
        return this;
    }

    public S2CTirePressureInfo setRBTemp(Integer num) {
        this.rBTemp = num;
        return this;
    }

    public S2CTirePressureInfo setRBTirePressure(Float f) {
        this.rBTirePressure = f;
        return this;
    }

    public S2CTirePressureInfo setRFTemp(Integer num) {
        this.rFTemp = num;
        return this;
    }

    public S2CTirePressureInfo setRFTirePressure(Float f) {
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

    public S2CTirePressureInfo() {
        super(ExCmd.S2C_TIRE_PRESSURE_INFO);
    }
}
