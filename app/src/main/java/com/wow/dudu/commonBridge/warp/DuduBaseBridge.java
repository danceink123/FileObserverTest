package com.wow.dudu.commonBridge.warp;

public abstract class DuduBaseBridge {
    public abstract BaseWarp decodeJson(short s, String str);

    public abstract String encodedJson(BaseWarp baseWarp);

    public String encoded(BaseWarp baseWarp) {
        return ((int) baseWarp.cmd) + "" + encodedJson(baseWarp);
    }

    public BaseWarp decode(String str) {
        int indexOf = str.indexOf("{");
        return decodeJson(Short.parseShort(str.substring(0, indexOf)), str.substring(indexOf));
    }
}
