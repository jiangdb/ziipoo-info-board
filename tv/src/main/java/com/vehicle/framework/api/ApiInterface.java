package com.vehicle.framework.api;

import com.vehicle.framework.api.responses.EtouchWeatherResponse;
import com.vehicle.framework.api.responses.HelpWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenxh on 10/5/18.
 */

public interface ApiInterface {
    @GET("http://wthrcdn.etouch.cn/weather_mini") //get temp,temp up,temp down and type
    Call<EtouchWeatherResponse> getWeatherByCity(@Query("city") String city);

//    @GET("http://wthrcdn.etouch.cn/weather_mini")
//    Call<EtouchWeatherResponse> getWeatherByCityKey(@Query("citykey") String citykey);

    @GET("http://api.help.bj.cn/apis/weather/")//get windspeed and humidity
    Call<HelpWeatherResponse> getWeather(@Query("id") String city);



}
