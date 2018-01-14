package com.mediana.medtemplate.subscription.telephony;
/**
 * Created by IT-10 on 10/8/2017.
 */

public abstract class MCIConstant{
    public static final String IP = "79.175.138.66";
    public static final String PORT = "8080";
    // TODO: 1/3/2018 replace these two comments with valid values
    public static final String USER_NAME = "";/*BuildConfig.HAMRAHVAS_USERNAME;*/
    public static final String PASSWORD = "";/*BuildConfig.HAMRAHVAS_PASSWORD;*/
    public static final String API_ROOT = "http://" + IP + ":" + PORT + "/OTP/";
    public static final String REQUEST_OTP = "Push";
    public static final String REQUEST_SUBSCRIPTION = "Charge";
    public static final String CANCEL_SUBSCRIPTION = "Push";
    public static final int SERVICE_ID = 2632;
}
