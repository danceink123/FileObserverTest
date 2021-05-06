package com.wow.dudu.commonBridge.warp.dcmusic.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class S2CMusicWaveFormData extends BaseWarp {
    private int rms;

    public S2CMusicWaveFormData setRms(int i) {
        this.rms = i;
        return this;
    }

    public int getRms() {
        return this.rms;
    }

    public S2CMusicWaveFormData() {
        super(DcmusicCmd.S2C_MUSIC_WAVE_FORM_DATA);
    }
}
