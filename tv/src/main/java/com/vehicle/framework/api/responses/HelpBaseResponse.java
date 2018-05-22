package com.vehicle.framework.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chenxh on 10/5/18.
 */

public class HelpBaseResponse {
    @SerializedName("status")
    @Expose
    protected String status;
    @SerializedName("msg")
    @Expose
    protected String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String desc) {
        this.msg = msg;
    }

}
