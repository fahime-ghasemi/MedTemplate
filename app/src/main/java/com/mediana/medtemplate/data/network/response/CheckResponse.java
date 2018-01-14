package com.mediana.medtemplate.data.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IT-10 on 1/8/2018.
 */

public class CheckResponse {
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
