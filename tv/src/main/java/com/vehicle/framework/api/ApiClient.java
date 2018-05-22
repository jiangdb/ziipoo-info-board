package com.vehicle.framework.api;

import com.vehicle.network.interceptors.HeaderInterceptor;
import com.vehicle.network.interceptors.QueryInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static ApiClient apiClient;
    private Retrofit retrofit;
    private ApiInterface apiInterface;


    private ApiClient(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //setup headers
        Map<String, String> headers = new HashMap(){{

        }};
        client.addInterceptor(new HeaderInterceptor(headers));

        //setup common queries
        Map<String, String> queries = new HashMap(){{

        }};
        client.addInterceptor(new QueryInterceptor(queries));

        retrofit = new Retrofit.Builder()
                .baseUrl("http://wthrcdn.etouch.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    public static ApiClient getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
