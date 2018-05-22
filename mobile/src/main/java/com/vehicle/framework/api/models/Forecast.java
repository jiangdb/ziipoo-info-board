package com.vehicle.framework.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Forecast implements Serializable, Parcelable {
    private static final long serialVersionUID = 1L;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("fengxiang")
    @Expose
    private String fengxiang;
    @SerializedName("fengli")
    @Expose
    private String fengli;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getHighTemperature() {
        return high;
    }

    public void setHighTemperature(String high) {
        this.high = high;
    }
    public String getLowTemperature() {
        return low;
    }

    public void setLowTemperature(String low) {
        this.low = low;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getFengXiang() {
        return fengxiang;
    }

    public void setFengXiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }
    public String getFengLi() {
        return fengli;
    }

    public void setFengLi(String fengli) {
        this.fengli = fengli;
    }


    public Forecast(Parcel in) {
        super();
        this.date = in.readString();
        this.high = in.readString();
        this.low = in.readString();
        this.type = in.readString();
        this.fengxiang = in.readString();
        this.fengli = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(date);
        dest.writeString(high);
        dest.writeString(low);
        dest.writeString(type);
        dest.writeString(fengxiang);
        dest.writeString(fengli);
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

}
