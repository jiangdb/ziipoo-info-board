package com.vehicle.framework.api;

/**
 * Created by chenxh on 10/5/18.
 */

public interface ApiCallback<T> {

    void onResponse(T t);

    void onError(ApiError error);

}
