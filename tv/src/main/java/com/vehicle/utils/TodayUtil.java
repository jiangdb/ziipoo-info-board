package com.vehicle.utils;

import com.vehicle.forecast.R;

public class TodayUtil {
    public static String getHighTemperature(String high){
        String[] temp = high.split(" ");
        return temp[1];
    }

    public static String getLowTemperature(String low){
        String[] temp = low.split(" ");
        return temp[1];
    }

    public static String getCityWithParent(String city){
        return city + "|中国";
    }

    public static int getWeatherTypeImage(String type){
        //晴，大雨，中雨，多云，阴，小雨,阵雨,雷阵雨
        int id = -1;
        if (type.equals("晴")){
            id = R.mipmap.sunny;
        }else if(type.equals("多云")){
            id = R.mipmap.cloudy;
        }else if(type.equals("大雨")){
            id = R.mipmap.heavy_rain;
        }else if(type.equals("中雨")){
            id = R.mipmap.moderate_rain;
        }else if(type.equals("阴")){
            id = R.mipmap.overcast;
        }else if(type.equals("小雨")){
            id = R.mipmap.light_rain;
        }else if(type.equals("阵雨")){//
            id = R.mipmap.light_rain;
        }else if(type.equals("雷阵雨")){//
            id = R.mipmap.light_rain;
        }else if(type.contains("雨") && type.contains("雪")){//
            id = R.mipmap.rain_snow;
        }else if(type.equals("雪")){//
            id = R.mipmap.snow;
        }else if(type.contains("大风")){//
            id = R.mipmap.high_wind;
        }else if(type.contains("闪电")){//
            id = R.mipmap.lightning;
        }else {
            id = R.mipmap.sunny;
        }
        return id;
    }
}
