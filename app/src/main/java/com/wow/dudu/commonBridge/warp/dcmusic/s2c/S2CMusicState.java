package com.wow.dudu.commonBridge.warp.dcmusic.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class S2CMusicState extends BaseWarp {
    private boolean run;

    public S2CMusicState setRun(boolean z) {
        this.run = z;
        return this;
    }

    public boolean isRun() {
        return this.run;
    }

    public S2CMusicState() {
        super(DcmusicCmd.S2C_MUSIC_STATE);
    }
}
