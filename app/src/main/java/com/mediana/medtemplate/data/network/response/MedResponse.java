package com.mediana.medtemplate.data.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 12/10/2017.
 */

public class MedResponse<T> {
    @SerializedName("data")
    public T data;
    @SerializedName("status")
    private int status;
    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return The data
     */
    public T getData() {
        return data;
    }
}
