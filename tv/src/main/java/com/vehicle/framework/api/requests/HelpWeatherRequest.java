package com.vehicle.framework.api.requests;

import com.vehicle.framework.api.ApiCallback;
import com.vehicle.framework.api.responses.HelpWeatherResponse;

/**
 * Created by chenxh on 10/5/18.
 */

public class HelpWeatherRequest extends HelpBaseRequest {
    private HelpWeatherRequest(ApiCallback<HelpWeatherResponse> apiCallback,String city) {
        super(apiCallback);
        apiInterface.getWeather(city).enqueue(callback);
    }

    @Override
    protected void onApiSuccess(Object response) {
        if (apiCallback != null) {
            apiCallback.onResponse(response);
        }
    }

    public static void load(String city,
                            ApiCallback<HelpWeatherResponse> apiCallback) {
        new HelpWeatherRequest(apiCallback, city);
    }

}
