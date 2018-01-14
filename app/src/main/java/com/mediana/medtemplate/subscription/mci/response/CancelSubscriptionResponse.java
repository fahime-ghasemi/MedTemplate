package com.mediana.medtemplate.subscription.mci.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 9/27/2017.
 */

public class CancelSubscriptionResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("statusDescription")
    @Expose
    private String statusDescription;

    @SerializedName("imiResult")
    @Expose
    private String imiResult;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getImiResult() {
        return imiResult;
    }

    public void setImiResult(String imiResult) {
        this.imiResult = imiResult;
    }
}
