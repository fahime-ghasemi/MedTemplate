package com.mediana.medtemplate.subscription.mci.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 10/9/2017.
 */

public class IMIResultResponse {
    @SerializedName("otpTransactionId")
    @Expose
    private String otpTransactionId;

    @SerializedName("statusInfo")
    @Expose
    private StatusInfoResponse statusInfoResponse;

    public String getOtpTransactionId() {
        return otpTransactionId;
    }

    public void setOtpTransactionId(String otpTransactionId) {
        this.otpTransactionId = otpTransactionId;
    }

    public StatusInfoResponse getStatusInfoResponse() {
        return statusInfoResponse;
    }

    public void setStatusInfoResponse(StatusInfoResponse statusInfoResponse) {
        this.statusInfoResponse = statusInfoResponse;
    }
}
