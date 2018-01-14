package com.mediana.medtemplate.data;

import com.mediana.medtemplate.data.network.response.CheckResponse;

import io.reactivex.Observable;

/**
 * Created by IT-10 on 12/10/2017.
 */
//write all api calls here
public interface ApiClient {
    Observable<String> getUser();

    Observable<CheckResponse> checkMCISubscription(String phone);
}
