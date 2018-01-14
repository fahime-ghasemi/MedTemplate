package com.mediana.medtemplate.subscription;

import javax.annotation.Nonnull;

/**
 * Created by IT-10 on 1/3/2018.
 */

public interface RequestListener<R> {
    /**
     * Called when the request has finished successfully.
     *
     * @param result request result
     */
    void onSuccess(@Nonnull R result);

    /**
     * Called when the request has finished with an error (for example, exception was raised).
     *
     * @param errorMessage message
     */
    void onError(int errorMessage);
}

