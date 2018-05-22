package com.vehicle.framework.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class WeatherInfo implements Serializable, Parcelable {
    private static final long serialVersionUID = 1L;
    private Forecast forecast;
    private String humidity;
    private String wdspd;
    private String pm25;
    private String city;
    public Forecast getForecast() {
        return forecast;
    }

    public void stForecast(String Forecast) {
        this.forecast = forecast;
    }

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

    public String getPM25() {
        return pm25;
    }

    public void setPM25(String pm25) {
        this.pm25 = pm25;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherInfo(Parcel in) {
        super();
        this.forecast = (Forecast)in.readSerializable();
        this.humidity = in.readString();
        this.wdspd = in.readString();
        this.pm25 = in.readString();
        this.city = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeSerializable(forecast);
        dest.writeString(humidity);
        dest.writeString(wdspd);
        dest.writeString(pm25);
        dest.writeString(city);
    }

    public static final Creator<WeatherInfo> CREATOR = new Creator<WeatherInfo>() {
        @Override
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }
    };

}
