package com.mediana.medtemplate.subscription;


import com.mediana.medtemplate.data.UserConfig;
import com.mediana.medtemplate.di.scope.ApplicationScope;
import com.mediana.medtemplate.subscription.store.BazaarSubscription;
import com.mediana.medtemplate.subscription.telephony.MTNSubscription;
import com.mediana.medtemplate.subscription.telephony.MCISubscription;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by IT-10 on 10/8/2017.
 */
@ApplicationScope
public class SubscriptionManager {
    Map<Integer, Subscription> subscriptions;

    @Inject
    public SubscriptionManager() {
        subscriptions = new HashMap<>();
        MTNSubscription irancellSubscription = new MTNSubscription();
        irancellSubscription.setStatus(UserConfig.MTNSubscripbtion ? Subscription.STATUS_SUBSCRIBED : Subscription.STATUS_UNSUBSCRIBED);
        irancellSubscription.setPhoneNumber(UserConfig.MTNSubscripbtion ? UserConfig.phoneNumber : "");
        subscriptions.put(Subscription.TYPE_MTN, irancellSubscription);

        MCISubscription mciSubscription = new MCISubscription();
        mciSubscription.setStatus(UserConfig.MCISubscripbtion ? Subscription.STATUS_SUBSCRIBED : Subscription.STATUS_UNSUBSCRIBED);
        mciSubscription.setPhoneNumber(UserConfig.MCISubscripbtion ? UserConfig.phoneNumber : "");
        subscriptions.put(Subscription.TYPE_MCI, mciSubscription);

        BazaarSubscription bazaarSubscription = new BazaarSubscription();
        bazaarSubscription.setStatus(UserConfig.bazarSubscriptionTime > 0 ? Subscription.STATUS_SUBSCRIBED : Subscription.STATUS_UNSUBSCRIBED);
        subscriptions.put(Subscription.TYPE_BAZAAR, bazaarSubscription);
    }

    public boolean isUserPremium() {
        for (Subscription subscription :
                subscriptions.values()) {
            if (subscription.getStatus() == Subscription.STATUS_SUBSCRIBED)
                return true;
        }
        return false;
    }

    public boolean checkSubsctiption(int type) {
        Subscription subscription = subscriptions.get(type);
        if (subscription != null && subscription.getStatus() == Subscription.STATUS_SUBSCRIBED)
            return true;
        return false;
    }

    public Subscription getSubscription(int type) {
        return subscriptions.get(type);
    }

    public void updateSubscription(int type, Subscription subscription) {
        if (type == Subscription.TYPE_MCI) {
            UserConfig.MCISubscripbtion = (subscription.getStatus() == Subscription.STATUS_SUBSCRIBED);
            UserConfig.phoneNumber = (((MCISubscription) subscription).getPhoneNumber());
            subscriptions.put(type, subscription);
        } else if (type == Subscription.TYPE_MTN) {
            UserConfig.MTNSubscripbtion = (subscription.getStatus() == Subscription.STATUS_SUBSCRIBED);
            UserConfig.phoneNumber = (((MTNSubscription) subscription).getPhoneNumber());
            subscriptions.put(type, subscription);
        } else if (type == Subscription.TYPE_BAZAAR) {
            UserConfig.bazarSubscripbtion = (subscription.getStatus() == Subscription.STATUS_SUBSCRIBED);
            UserConfig.bazarSubscriptionTime = ((BazaarSubscription) subscription).getMinuteExpireTime();
            subscriptions.put(type, subscription);
        }

    }
}
