package com.vehicle.network.interceptors;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenxh on 10/5/18.
 */

public class QueryInterceptor implements Interceptor {
    Map<String, String> queries;

    public QueryInterceptor(Map<String, String> queries) {
        this.queries = queries;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (queries != null) {
            HttpUrl.Builder urlBuilder = request.url().newBuilder();
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
            request = request.newBuilder().url(urlBuilder.build()).build();
            return chain.proceed(request);
        }

        return chain.proceed(request);
    }
}
