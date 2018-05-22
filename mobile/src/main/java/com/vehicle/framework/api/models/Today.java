package com.vehicle.framework.api.models;

public class Today {
    private static Today mInstance = null;
    private String mCity;
    private String mHumidity;
    private String mWdspd;
    private String mPm25;
    private String mHigh;
    private String mLow;
    private String mType;
    private String mTemp;

    public static synchronized Today getInstance() {
        if(mInstance == null) {
            mInstance = new Today();
        }
        return mInstance;
    }

    public String getCity() {
        return mCity;
    }

    public Today setCity(String city) {
        mCity = city;
        return this;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public Today setHumidity(String humidity) {
        mHumidity = humidity;
        return this;
    }

    public String getWindSpeed() {
        return mWdspd;
    }

    public Today setWindSpeed(String wdspd) {
        mWdspd = wdspd;
        return this;
    }

    public String getHighTemperature() {
        return mHigh;
    }

    public Today setHighTemperature(String high) {
        mHigh = high;
        return this;
    }

    public String getLowTemperature() {
        return mLow;
    }

    public Today setLowTemperature(String low) {
        mLow = low;
        return this;
    }

    public String getCurrentTemperature() {
        return mTemp;
    }

    public Today setCurrentTemperature(String temp) {
        mTemp = temp;
        return this;
    }

    public String getWeatherType() {
        return mCity;
    }

    public Today setWeatherType(String type) {
        mType = type;
        return this;
    }

    public String getPM25() {
        return mPm25;
    }

    public Today setPM25(String pm25) {
        mPm25 = pm25;
        return this;
    }

    private Today() {
    }
}
