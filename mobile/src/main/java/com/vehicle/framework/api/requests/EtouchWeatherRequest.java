package com.vehicle.framework.api.requests;


import com.vehicle.framework.api.ApiCallback;
import com.vehicle.framework.api.responses.EtouchWeatherResponse;

/**
 * Created by chenxh on 10/5/18.
 */

public class EtouchWeatherRequest extends BaseRequest {
    private EtouchWeatherRequest(ApiCallback<EtouchWeatherResponse> apiCallback, String city) {
        super(apiCallback);
        apiInterface.getWeatherByCity(city).enqueue(callback);
    }

    @Override
    protected void onApiSuccess(Object response) {
        if (apiCallback != null) {
            apiCallback.onResponse(response);
        }
    }

    public static void load(String city,
                            ApiCallback<EtouchWeatherResponse> apiCallback) {
        new EtouchWeatherRequest(apiCallback, city);
    }

}
