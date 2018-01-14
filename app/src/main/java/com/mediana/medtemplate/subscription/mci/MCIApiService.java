package com.mediana.medtemplate.subscription.mci;

import com.mediana.medtemplate.subscription.mci.response.CancelSubscriptionResponse;
import com.mediana.medtemplate.subscription.telephony.MCIConstant;
import com.mediana.medtemplate.subscription.mci.response.*;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by IT-10 on 9/27/2017.
 */

public interface MCIApiService {
    @Headers("Accept: application/json")
    @POST(MCIConstant.REQUEST_OTP)
    @FormUrlEncoded
    Observable<RequestOtpResponse> requestOTP(@Query("username") String username, @Query("password") String password,
                                              @Field("cellPhoneNumber") String cellPhoneNumber, @Field("serviceId") int serviceId,
                                              @Field("chargeCodeNumber") String chargeCodeNumber, @Field("price") String price, @Field("cpUniqueToken") String cpUniqueToken,
                                              @Field("description") String description, @Field("content") String content);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(MCIConstant.REQUEST_SUBSCRIPTION)
    Observable<RequestSubscriptionResponse> requestSubscription(@Query("username") String username, @Query("password") String password,
                                                                @Field("cellPhoneNumber") String phone, @Field("transactionPIN") String code, @Field("otpTransactionId") String OTPTransactionId,
                                                                @Field("serviceId") int serviceId, @Field("cpUniqueToken") String cpUniqueToken, @Field("content") String content);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(MCIConstant.CANCEL_SUBSCRIPTION)
    Observable<CancelSubscriptionResponse> cancelSubscription(@Query("username") String username, @Query("password") String password,
                                                              @Field("cellPhoneNumber") String phone, @Field("serviceId") int serviceId, @Field("chargeCodeNumber") String chargeCodeNumber, @Field("price") String price, @Field("cpUniqueToken") String cpUniqueToken,
                                                              @Field("description") String description, @Field("content") String content);

}
