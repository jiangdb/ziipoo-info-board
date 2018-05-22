package com.vehicle.framework.api;

/**
 * Created by chenxh on 10/5/18.
 */

public class ApiError extends Exception{

    public String message;
    public int code;
    public ERROR error;

    public enum ERROR {
        REQUEST_ERROR,
        SERVER_ERROR,
        NETWORK_ERROR
    }

    public ApiError(ERROR error, int code, String message){
        this.code = code;
        this.message = message;
        this.error = error;
    }

    @Override
    public String toString() {
        return "message: "+message + " code:"+code + " error:"+error;
    }
}
