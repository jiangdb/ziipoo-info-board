package com.vehicle.framework.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chenxh on 10/5/18.
 */

public class HelpWeatherResponse extends HelpBaseResponse{
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("wdspd")
    @Expose
    private String wdspd;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("uptime")
    @Expose
    private String uptime;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("pm25")
    @Expose
    private String pm25;
    @SerializedName("aqi")
    @Expose
    private String aqi;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return wdspd;
    }

    public void setWindSpeed(String wdspd) {
        this.wdspd = wdspd;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return uptime;
    }

    public void setUpdateTime(String uptime) {
        this.uptime = uptime;
    }

    public String getTemperature() {
        return temp;
    }

    public void setTemperature(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPM25() {
        return pm25;
    }

    public void setPM25(String pm25) {
        this.pm25 = pm25;
    }

    public String getAQI() {
        return aqi;
    }

    public void setAQI(String aqi) {
        this.aqi = aqi;
    }
}
