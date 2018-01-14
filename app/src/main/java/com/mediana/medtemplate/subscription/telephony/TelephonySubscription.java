package com.mediana.medtemplate.subscription.telephony;

import com.mediana.medtemplate.subscription.Subscription;

/**
 * Created by IT-10 on 10/8/2017.
 */

public class TelephonySubscription extends Subscription {
    String phoneNumber;
    public TelephonySubscription(int type) {
        super(type);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
