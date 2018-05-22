package com.vehicle.framework.api.responses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vehicle.framework.api.models.Forecast;

/**
 * Created by chenxh on 10/5/18.
 */

public class EtouchWeatherResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private JsonObject data;



    public JsonObject getRawData() {
        return data;
    }

    public void setRawData(JsonObject data) {
        this.data = data;
    }

    public String getWenDu() {

        return data.get("wendu").getAsString();
    }

    public String getCity() {

        return data.get("city").getAsString();
    }

    public String getGanMao() {

        return data.get("ganmao").getAsString();
    }

    public String getAQI() {

        return data.get("aqi").getAsString();
    }

    public JsonObject getYesterday() {

        return data.get("yesterday").getAsJsonObject();
    }

    public JsonObject getToday() {

        return data.get("forecast").getAsJsonArray().get(0).getAsJsonObject();
    }

    public JsonArray getForecasts() {

        return data.get("forecast").getAsJsonArray();
    }


}
