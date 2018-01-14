package com.mediana.medtemplate.subscription;

/**
 * Created by IT-10 on 10/8/2017.
 */

public interface SubscriptionListener {
    void onSubscriptionFinished();
    void onSubscriptionCanceled();
    void onSubscriptionStart();
}
