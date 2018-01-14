package com.mediana.medtemplate.events;

import android.support.annotation.StringRes;

/**
 * Created by IT-10 on 12/9/2017.
 */

public class ShowConnectionProblemEvent {
    public int title;
    public int message;

    public ShowConnectionProblemEvent(@StringRes int title,@StringRes int message) {
        this.title = title;
        this.message = message;

    }
}
