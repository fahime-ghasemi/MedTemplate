package com.mediana.medtemplate.subscription.mci.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 10/9/2017.
 */

public class ErrorInfoResponse {
    @SerializedName("errorCode")
    @Expose
    private String errorCode;

    @SerializedName("errorDescription")
    @Expose
    private String errorDescription;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
