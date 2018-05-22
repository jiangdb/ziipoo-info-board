package com.vehicle.network.interceptors;

import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenxh on 10/5/18.
 */

public class HeaderInterceptor implements Interceptor {
    Map<String, String> headers;

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (headers != null) {
            Request.Builder builder = request.newBuilder();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
            return chain.proceed(builder.build());
        }
        return chain.proceed(request);
    }
}
