package com.wow.dudu.commonBridge.warp.dcmusic.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class S2CMusicProgress extends BaseWarp {
    private int progress;
    private int total;

    public S2CMusicProgress setProgress(int i) {
        this.progress = i;
        return this;
    }

    public S2CMusicProgress setTotal(int i) {
        this.total = i;
        return this;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getTotal() {
        return this.total;
    }

    public S2CMusicProgress() {
        super(DcmusicCmd.S2C_MUSIC_PROGRESS);
    }
}
