package com.mediana.medtemplate.data.network;

import com.annimon.stream.internal.Params;
import com.mediana.medtemplate.data.network.response.CheckResponse;
import com.mediana.medtemplate.data.network.response.MedResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by IT-10 on 12/11/2017.
 */

public interface NetworkApiService {
    @GET(Url.GET_USER)
    Observable<MedResponse<String>> getUser();

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(Url.CHECK_SUBSCRIPTION_MCI)
    Observable<MedResponse<CheckResponse>> checkMCISubscription(@Field(Param.MOBILE) String tell);

//    @Headers("Accept: application/json")
//    @FormUrlEncoded
//    @POST("category/channels")
//    Observable<MedResponse<CategoryChannelsData>> getChannelsFromCategory(@Query(Params.TOKEN) String token, @Query(Params.PAGE) int page, @Field(Params.ID) int id, @Field(Params.DEVICE_ID) String deviceID);
}
