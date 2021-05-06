package com.wow.dudu.commonBridge.warp;

public interface FromJsonInterface {
    <T extends BaseWarp> T fromJson(String str, Class<T> cls);
}
