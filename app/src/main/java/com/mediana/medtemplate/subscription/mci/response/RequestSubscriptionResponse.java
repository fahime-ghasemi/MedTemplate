package com.mediana.medtemplate.subscription.mci.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 9/27/2017.
 */

public class RequestSubscriptionResponse {
    @SerializedName("cpUniqueToken")
    @Expose
    private String cpUniqueToken;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("statusDescription")
    @Expose
    private String statusDescription;

    @SerializedName("otpTransactionId")
    @Expose
    private String otpTransactionId;

    @SerializedName("destinationResult")
    @Expose
    private String destinationResult;

    public String getCpUniqueToken() {
        return cpUniqueToken;
    }

    public void setCpUniqueToken(String cpUniqueToken) {
        this.cpUniqueToken = cpUniqueToken;
    }

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

    public String getOtpTransactionId() {
        return otpTransactionId;
    }

    public void setOtpTransactionId(String otpTransactionId) {
        this.otpTransactionId = otpTransactionId;
    }

    public String getDestinationResult() {
        return destinationResult;
    }

    public void setDestinationResult(String destinationResult) {
        this.destinationResult = destinationResult;
    }
}
