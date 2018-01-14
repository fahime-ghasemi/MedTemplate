package com.mediana.medtemplate.data.network;

import android.support.annotation.Nullable;

import com.mediana.medtemplate.BaseView;
import com.mediana.medtemplate.R;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by IT-10 on 1/7/2018.
 */

public abstract class MyDisposableObserver<T> extends DisposableObserver<T> {

    @Nullable
    private BaseView baseView;

    protected MyDisposableObserver(@Nullable BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            if (null != baseView) {
                baseView.showSnackBar(R.string.error_connection_msg);
            }
        }
    }
}
