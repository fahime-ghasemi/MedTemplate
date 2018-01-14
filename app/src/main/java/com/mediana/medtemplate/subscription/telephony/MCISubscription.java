package com.mediana.medtemplate.subscription.telephony;

import com.mediana.medtemplate.subscription.Subscription;

/**
 * Created by IT-10 on 1/3/2018.
 */

public class MCISubscription extends TelephonySubscription {
    public MCISubscription() {
        super(Subscription.TYPE_MCI);
    }
}
