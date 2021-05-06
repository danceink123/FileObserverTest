package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CNavInfo extends BaseWarp {
    private int curRemainDis = 0;
    private int curRouteRemainDis = 0;
    private int curRouteRemainTime = 0;
    private int icon = 0;
    private String iconMsg = "";
    private String nextRoadName = "";
    private boolean state = false;

    public S2CNavInfo setCurRemainDis(int i) {
        this.curRemainDis = i;
        return this;
    }

    public S2CNavInfo setCurRouteRemainDis(int i) {
        this.curRouteRemainDis = i;
        return this;
    }

    public S2CNavInfo setCurRouteRemainTime(int i) {
        this.curRouteRemainTime = i;
        return this;
    }

    public S2CNavInfo setIcon(int i) {
        this.icon = i;
        return this;
    }

    public S2CNavInfo setIconMsg(String str) {
        this.iconMsg = str;
        return this;
    }

    public S2CNavInfo setNextRoadName(String str) {
        this.nextRoadName = str;
        return this;
    }

    public S2CNavInfo setState(boolean z) {
        this.state = z;
        return this;
    }

    public boolean isState() {
        return this.state;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getIconMsg() {
        return this.iconMsg;
    }

    public int getCurRemainDis() {
        return this.curRemainDis;
    }

    public String getNextRoadName() {
        return this.nextRoadName;
    }

    public int getCurRouteRemainTime() {
        return this.curRouteRemainTime;
    }

    public int getCurRouteRemainDis() {
        return this.curRouteRemainDis;
    }

    public S2CNavInfo() {
        super(DriverCmd.S2C_NAV_INFO);
    }
}
