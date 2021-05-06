package com.wow.dudu.commonBridge.warp.dcmusic.c2s;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.dcmusic.DcmusicCmd;
import java.util.List;

public class ResMusicList extends BaseWarp {
    private List<Music> musics;

    public ResMusicList setMusics(List<Music> list) {
        this.musics = list;
        return this;
    }

    public ResMusicList() {
        super(DcmusicCmd.RES_MUSIC_LIST);
    }

    public List<Music> getMusics() {
        return this.musics;
    }

    public static class Music {

        /* renamed from: a */
        private String f106a = "";

        /* renamed from: i */
        private int f107i = 0;

        /* renamed from: t */
        private String f108t = "";

        public Music setA(String str) {
            this.f106a = str;
            return this;
        }

        public Music setI(int i) {
            this.f107i = i;
            return this;
        }

        public Music setT(String str) {
            this.f108t = str;
            return this;
        }

        public String toString() {
            return "ResMusicList.Music(i=" + getI() + ", t=" + getT() + ", a=" + getA() + ")";
        }

        public int getI() {
            return this.f107i;
        }

        public String getT() {
            return this.f108t;
        }

        public String getA() {
            return this.f106a;
        }
    }
}
