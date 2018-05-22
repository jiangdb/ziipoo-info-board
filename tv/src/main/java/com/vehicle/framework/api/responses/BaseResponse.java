package com.vehicle.framework.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chenxh on 10/5/18.
 */

public class BaseResponse {
    @SerializedName("status")
    @Expose
    protected String status;
    @SerializedName("desc")
    @Expose
    protected String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

}
