package com.mediana.medtemplate.subscription;

/**
 * Created by IT-10 on 10/8/2017.
 */

public abstract class Subscription {

    public static final int STATUS_SUBSCRIBED = 1;
    public static final int STATUS_UNSUBSCRIBED = 2;
    public static final int TYPE_MTN = 1;
    public static final int TYPE_MCI = 2;
    public static final int TYPE_BAZAAR = 3;
    public static final int TYPE_BANK = 4;
    public static final int TYPE_GOOGLE = 5;
    public static final int TYPE_MYKET = 5;
    public static final int TYPE_RITHEL= 6;
    protected int status;
    protected int type;
    protected boolean sendToServer;
    SubscriptionListener listener;

    public Subscription(int type) {
        this.type = type;
    }

    public SubscriptionListener getListener() {
        return listener;
    }

    public void setListener(SubscriptionListener listener) {
        this.listener = listener;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSendToServer() {
        return sendToServer;
    }

    public void setSendToServer(boolean sendToServer) {
        this.sendToServer = sendToServer;
    }

    public int getType() {
        return type;
    }
}
