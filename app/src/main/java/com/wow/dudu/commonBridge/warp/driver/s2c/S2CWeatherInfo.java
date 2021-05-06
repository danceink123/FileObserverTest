package com.wow.dudu.commonBridge.warp.driver.s2c;

import com.wow.dudu.commonBridge.warp.BaseWarp;
import com.wow.dudu.commonBridge.warp.driver.DriverCmd;

public class S2CWeatherInfo extends BaseWarp {
    private String city;
    private String district;
    private int temperature;
    private String weather;

    public S2CWeatherInfo setCity(String str) {
        this.city = str;
        return this;
    }

    public S2CWeatherInfo setDistrict(String str) {
        this.district = str;
        return this;
    }

    public S2CWeatherInfo setTemperature(int i) {
        this.temperature = i;
        return this;
    }

    public S2CWeatherInfo setWeather(String str) {
        this.weather = str;
        return this;
    }

    public String getWeather() {
        return this.weather;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public String getCity() {
        return this.city;
    }

    public String getDistrict() {
        return this.district;
    }

    public S2CWeatherInfo() {
        super(DriverCmd.S2C_WEATHER_INFO);
    }
}
