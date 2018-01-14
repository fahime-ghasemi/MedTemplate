package com.mediana.medtemplate.subscription;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.gson.Gson;
import com.mediana.medtemplate.R;
import com.mediana.medtemplate.data.ApiClient;
import com.mediana.medtemplate.data.network.response.CheckResponse;
import com.mediana.medtemplate.di.scope.ApplicationScope;
import com.mediana.medtemplate.subscription.mci.MCIApiService;
import com.mediana.medtemplate.subscription.mci.response.IMIResultResponse;
import com.mediana.medtemplate.subscription.mci.response.RequestOtpResponse;
import com.mediana.medtemplate.subscription.mci.response.RequestSubscriptionResponse;
import com.mediana.medtemplate.subscription.telephony.MCIConstant;
import com.mediana.medtemplate.subscription.telephony.MCISubscription;

import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


/**
 * Created by IT-10 on 1/3/2018.
 */
@ApplicationScope
public class MCIBilling implements Billing {
    private ApiClient apiClient;
    private MCIApiService mciApiService;
    private String cpUniqueToken;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private long lastSuccessfulOTPRequestTime = 0;
    private FragmentManager fragmentManager;
    private SubscriptionManager subscriptionManager;
    private String otpTransactionId = "";
    private static final String LOG_TAG = MCIBilling.class.getSimpleName();
    private VerificationCodeDialog verificationCodeDialog;
    private String phoneNumber = "";
    private RequestListener listener;

    @Inject
    public MCIBilling(FragmentManager fragmentManager, ApiClient apiClient, MCIApiService mciApiService, SubscriptionManager subscriptionManager) {
        this.fragmentManager = fragmentManager;
        this.apiClient = apiClient;
        this.mciApiService = mciApiService;
        this.verificationCodeDialog = VerificationCodeDialog.getInstance(verificationDialogListener);
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public void purchase(String phone, @NonNull RequestListener<Boolean> listener) {
        phoneNumber = phone;
        cpUniqueToken = UUID.randomUUID().toString();
        this.listener = listener;

        long currentTime = System.currentTimeMillis();
        long requestTimeThreshold = 4 * 60 * 1000;
        if ((currentTime - lastSuccessfulOTPRequestTime) < requestTimeThreshold) {
            verificationCodeDialog.show(fragmentManager, "");
            return;
        }
        disposable.add(mciApiService.requestOTP(MCIConstant.USER_NAME, MCIConstant.PASSWORD, phone, MCIConstant.SERVICE_ID, "0", "1", cpUniqueToken, "123", "123").subscribeWith(new DisposableObserver<RequestOtpResponse>() {
            @Override
            public void onNext(RequestOtpResponse response) {
                if (response.getStatus().equals("2")) {
                    Log.i(LOG_TAG, "requestOTP response successful");
                    lastSuccessfulOTPRequestTime = System.currentTimeMillis();
                    otpTransactionId = response.getOtpTransactionId();
                    verificationCodeDialog.show(fragmentManager, "");
                } else {

                    if (response.getStatus().equals("5") || response.getStatus().equals("4")) {
                        if (listener != null)
                            listener.onError(R.string.error_mci_number);
                    } else if (response.getDestinationResult() != null) {
                        try {
                            Log.i(LOG_TAG, response.getDestinationResult());
                            Gson gson = new Gson();
                            IMIResultResponse imiResultResponse = gson.fromJson(response.getDestinationResult(), IMIResultResponse.class);
                            if (imiResultResponse.getStatusInfoResponse().getErrorInfoResponse().getErrorCode().equals("51035")) {
                                if (listener != null)
                                    listener.onError(R.string.error_mci_repeated);
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.v(LOG_TAG,e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));

    }

    @Override
    public void purchase(int purchaseID, RequestListener<Boolean> listener) {

    }

    @Override
    public void hasPurchase(String phone, @NonNull RequestListener<Boolean> listener) {
        disposable.add(apiClient.checkMCISubscription(phone).subscribeWith(new DisposableObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse checkResponse) {
                listener.onSuccess(checkResponse.getResult().equals(CheckResponse.TRUE));

            }

            @Override
            public void onError(Throwable e) {
                Log.v(LOG_TAG, e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    @Override
    public void hasPurchase(int purchaseID, RequestListener<Boolean> listener) {

    }

    @Override
    public void release() {
        disposable.clear();
    }

    private VerificationCodeDialog.Listener verificationDialogListener = new VerificationCodeDialog.Listener() {
        @Override
        public void onDoneClick(String code) {
            cpUniqueToken = UUID.randomUUID().toString();
            disposable.add(mciApiService.requestSubscription(MCIConstant.USER_NAME, MCIConstant.PASSWORD, phoneNumber, code, otpTransactionId, MCIConstant.SERVICE_ID, cpUniqueToken, "123").subscribeWith(new DisposableObserver<RequestSubscriptionResponse>() {
                @Override
                public void onNext(RequestSubscriptionResponse response) {
                    if (response.getStatus().equals("2")) {
                        Log.i(LOG_TAG, "requestSubscription response successful");
                        MCISubscription subscription = (MCISubscription) subscriptionManager.getSubscription(Subscription.TYPE_MCI);
                        subscription.setPhoneNumber(phoneNumber);
                        subscription.setStatus(Subscription.STATUS_SUBSCRIBED);
                        subscriptionManager.updateSubscription(Subscription.TYPE_MCI, subscription);
                        if (listener != null)
                            listener.onSuccess(new Object());
                    } else {
                        if (response.getDestinationResult() != null) {
                            Log.i(LOG_TAG, response.getDestinationResult());

                            if (response.getStatus().equals("5") || response.getStatus().equals("4"))
                                if (listener != null) listener.onError(R.string.error_mci_number);
                            try {
                                Gson gson = new Gson();
                                IMIResultResponse imiResultResponse = gson.fromJson(response.getDestinationResult(), IMIResultResponse.class);
                                if (imiResultResponse.getStatusInfoResponse().getErrorInfoResponse().getErrorCode().equals("51026")) {
                                    if (listener != null)
                                        listener.onError(R.string.error_invalid_code);
                                } else if (imiResultResponse.getStatusInfoResponse().getErrorInfoResponse().getErrorCode().equals("123")) {
                                    if (listener != null)
                                        listener.onError(R.string.error_mci_active_service);
                                }
                            } catch (Exception e) {

                            }

                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            }));

        }
    };
}
