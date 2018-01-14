package com.mediana.medtemplate.subscription.store;

/**
 * Created by IT-10 on 10/8/2017.
 */

public class BazaarSubscription extends StoreSubscription {
    private int minuteExpireTime;
    public BazaarSubscription() {
        super(TYPE_BAZAAR);
    }

    public int getMinuteExpireTime() {
        return minuteExpireTime;
    }

    public void setMinuteExpireTime(int minuteExpireTime) {
        this.minuteExpireTime = minuteExpireTime;
    }
}
