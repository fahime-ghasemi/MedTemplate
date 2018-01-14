package com.mediana.medtemplate.subscription;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.billingclient.util.IabHelper;
import com.android.billingclient.util.IabResult;
import com.android.billingclient.util.Inventory;
import com.android.billingclient.util.Purchase;
import com.mediana.medtemplate.BaseActivity;
import com.mediana.medtemplate.BuildConfig;
import com.mediana.medtemplate.di.scope.ApplicationScope;
import com.mediana.medtemplate.subscription.telephony.MTNSubscription;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Created by IT-10 on 1/13/2018.
 */
@ApplicationScope
public class MTNBilling implements Billing {
    private IabHelper mHelper;
    private static final String LOG_TAG = MTNBilling.class.getSimpleName();
    private RequestListener listener;
    private static final String SKU = "mediana.jhoobin";
    private int purchaseID = 0;
    private boolean isInit = false;
    private SubscriptionManager subscriptionManager;
    private AppCompatActivity appCompatActivity;
    public static int REQUEST_CODE = 8870;
    private String phoneNumber;


    @Inject
    public MTNBilling(BaseActivity baseActivity, SubscriptionManager subscriptionManager) {
        this.appCompatActivity = baseActivity;
        this.subscriptionManager = subscriptionManager;
        mHelper = new IabHelper(appCompatActivity.getApplicationContext(), BuildConfig.base64);
    }

    @Override
    public void purchase(String phone, RequestListener<Boolean> listener) {

    }

    @Override
    public void purchase(int purchaseID, @NonNull RequestListener<Boolean> listener) {
        if (mHelper == null) throw new IllegalArgumentException("mHelper is null");
        this.listener = listener;
        if (isInit) {
            try {

                mHelper.launchPurchaseFlow(appCompatActivity, SKU + purchaseID, REQUEST_CODE, mPurchaseFinishedListener, this.phoneNumber);
            } catch (IabHelper.IabAsyncInProgressException exception) {
                Log.v(LOG_TAG, exception.getMessage());
            }
        } else {
            init(new RequestListener<Boolean>() {
                @Override
                public void onSuccess(@Nonnull Boolean result) {
                }

                @Override
                public void onError(int errorMessage) {
                    listener.onError(errorMessage);
                }
            });
        }

    }

    public boolean handleActivityResult(int resultCode, Intent data) {
        if (mHelper == null) throw new IllegalArgumentException("mHelper is null");
        return mHelper.handleActivityResult(REQUEST_CODE, resultCode, data);
    }

    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {

        @Override
        public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
            if (iabResult.isFailure()) {
                if (listener != null)
                    listener.onError(0);
            } else if (iabResult.isSuccess()) {
                MTNSubscription subscription = (MTNSubscription) subscriptionManager.getSubscription(Subscription.TYPE_MTN);
                subscription.setPhoneNumber(phoneNumber);
                subscription.setStatus(Subscription.STATUS_SUBSCRIBED);
                subscriptionManager.updateSubscription(Subscription.TYPE_MTN, subscription);
                if (listener != null)
                    listener.onSuccess(new Object());
            }
        }
    };

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("msisdn", phoneNumber);
        mHelper.setFillInIntent(fillInIntent);
    }

    @Override
    public void hasPurchase(String phone, RequestListener<Boolean> listener) {

    }

    @Override
    public void hasPurchase(int purchaseID, @NonNull RequestListener<Boolean> listener) {
        if (mHelper == null) throw new IllegalArgumentException("mHelper is null");
        if (isInit) {
            checkPurchase(purchaseID, listener);
        } else {
            init(new RequestListener<Boolean>() {
                @Override
                public void onSuccess(@Nonnull Boolean result) {
                    checkPurchase(purchaseID, listener);
                }

                @Override
                public void onError(int errorMessage) {
                    listener.onError(errorMessage);
                }
            });
        }

    }

    private void checkPurchase(int purchaseID, @NonNull RequestListener<Boolean> requestListener) {
        try {
            mHelper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() {
                @Override
                public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                    if (result.isFailure()) {
                        requestListener.onError(0);
                    } else {
                        Purchase purchase = inventory.getPurchase(SKU + purchaseID);
                        listener.onSuccess(null != purchase);
                    }
                }
            });
        } catch (IabHelper.IabAsyncInProgressException e) {
        }
    }

    private void init(@NonNull RequestListener<Boolean> requestListener) {
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(LOG_TAG, "Setup finished.");
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    if (listener != null) requestListener.onError(0);
                    return;
                }
                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) {
                    if (listener != null) requestListener.onError(0);
                    return;
                }
                isInit = true;
                requestListener.onSuccess(true);

            }
        });
    }

    @Override
    public void release() {

    }
}
