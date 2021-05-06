package com.wow.dudu.commonBridge.warp.dcmusic.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;

public class C2SMusicCmd extends BaseWarp {
    private int musicCmd;

    public C2SMusicCmd setMusicCmd(int i) {
        this.musicCmd = i;
        return this;
    }

    public int getMusicCmd() {
        return this.musicCmd;
    }

    public C2SMusicCmd() {
        super(DcmusicCmd.C2S_MUSIC_CMD);
    }
}
