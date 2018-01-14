package com.mediana.medtemplate.subscription;

import android.support.annotation.Nullable;

/**
 * Created by IT-10 on 1/3/2018.
 */

public interface Billing {
    void purchase(String phone,RequestListener<Boolean> listener);
    void purchase(int purchaseID,RequestListener<Boolean> listener);

    void hasPurchase(String phone, RequestListener<Boolean> listener);
    void hasPurchase(int purchaseID, RequestListener<Boolean> listener);

    void release();
}
