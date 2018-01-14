package com.mediana.medtemplate.subscription.mci.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 10/9/2017.
 */

public class StatusInfoResponse {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;

    @SerializedName("refrenceCode")
    @Expose
    private String refrenceCode;

    @SerializedName("serverRefrenceCode")
    @Expose
    private String serverRefrenceCode;

    @SerializedName("errorInfo")
    @Expose
    private ErrorInfoResponse errorInfoResponse;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRefrenceCode() {
        return refrenceCode;
    }

    public void setRefrenceCode(String refrenceCode) {
        this.refrenceCode = refrenceCode;
    }

    public String getServerRefrenceCode() {
        return serverRefrenceCode;
    }

    public void setServerRefrenceCode(String serverRefrenceCode) {
        this.serverRefrenceCode = serverRefrenceCode;
    }

    public ErrorInfoResponse getErrorInfoResponse() {
        return errorInfoResponse;
    }

    public void setErrorInfoResponse(ErrorInfoResponse errorInfoResponse) {
        this.errorInfoResponse = errorInfoResponse;
    }
}
