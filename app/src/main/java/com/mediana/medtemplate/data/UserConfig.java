package com.mediana.medtemplate.data;

/**
 * Created by IT-10 on 1/10/2018.
 */

public class UserConfig {
    public static String phoneNumber = "";
    public static boolean MCISubscripbtion = false;
    public static boolean MTNSubscripbtion = false;
    public static boolean rightelSubscripbtion = false;
    public static boolean bazarSubscripbtion = false;
    public static long bazarSubscriptionTime = 0;

    public static void clearConfig() {
        phoneNumber = "";
    }
}
