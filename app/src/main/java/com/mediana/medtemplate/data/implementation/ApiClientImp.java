package com.mediana.medtemplate.data.implementation;

import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.mediana.medtemplate.R;
import com.mediana.medtemplate.data.ApiClient;
import com.mediana.medtemplate.data.network.NetworkApiService;
import com.mediana.medtemplate.data.network.response.CheckResponse;
import com.mediana.medtemplate.data.network.response.MedResponse;
import com.mediana.medtemplate.events.ShowConnectionProblemEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.net.ssl.SSLException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

/**
 * Created by IT-10 on 12/10/2017.
 */

public class ApiClientImp implements ApiClient,Consumer<Throwable> {
    private final NetworkApiService networkApiService;
    private static final String LOG = ApiClientImp.class.getSimpleName();

    @SuppressWarnings("unchecked")
    private final ObservableTransformer apiCallTransformer = observable -> observable.map(new Function<MedResponse, Object>() {
        @Override
        public Object apply(MedResponse medResponse) throws Exception {

            return medResponse.getData();
        }
    })
//                    .map(new Func1<MedResponse, Object>() {
//                        @Override
//                        public Object call(MedResponse habitResponse) {
//                            return habitResponse.getData();
//                        }
//                    })
            .subscribeOn(Schedulers.io())
            .retryWhen(new RetryWithDelay()).doOnError(this)
            .observeOn(AndroidSchedulers.mainThread());

    @Inject
    public ApiClientImp(NetworkApiService networkApiService) {
        this.networkApiService = networkApiService;
    }

    @SuppressWarnings("unchecked")
    private <T> ObservableTransformer<MedResponse<T>, T> configureApiCallObserver() {
        return (ObservableTransformer<MedResponse<T>, T>) apiCallTransformer;
    }

    @Override
    public Observable<String> getUser() {
        return networkApiService.getUser().compose(configureApiCallObserver());
    }

    @Override
    public Observable<CheckResponse> checkMCISubscription(String phone) {
        return networkApiService.checkMCISubscription(phone).compose(configureApiCallObserver());
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        final Class<?> throwableClass = throwable.getClass();
        if (SocketException.class.isAssignableFrom(throwableClass) || SSLException.class.isAssignableFrom(throwableClass)) {
            this.showConnectionProblemDialog(R.string.internal_error_api);
        } else if (throwableClass.equals(SocketTimeoutException.class) || UnknownHostException.class.equals(throwableClass)) {
            this.showConnectionProblemDialog(R.string.error_connection_msg);
        } else if (JsonSyntaxException.class.isAssignableFrom(throwableClass)) {
            Log.v(LOG,"Json Error: " + throwable.getMessage());
        }
    }

    private void showConnectionProblemDialog(final int resourceMessageString) {
        showConnectionProblemDialog(R.string.network_error_title, resourceMessageString);
    }
    private void showConnectionProblemDialog(final int resourceTitleString, final int resourceMessageString) {
        ShowConnectionProblemEvent event = new ShowConnectionProblemEvent(resourceTitleString, resourceMessageString);
        EventBus.getDefault().post(event);
    }

    class RetryWithDelay implements
            Function<Observable<? extends Throwable>, ObservableSource<?>> {

        private final int maxRetries = 3;
        private int retryCount = 0;

        @Override
        public Observable<?> apply(Observable<? extends Throwable> attempts) throws Exception {
            return attempts
                    .flatMap((Function<Throwable, Observable<?>>) throwable -> {
                        if (throwable instanceof TimeoutException || throwable instanceof SocketTimeoutException) {
                            if (++retryCount < maxRetries) {
                                return Observable.timer(
                                        (long) Math.pow(2, retryCount),
                                        TimeUnit.SECONDS);
                            }
                        }
                        return Observable.error(throwable);
                    });
        }
    }

}
