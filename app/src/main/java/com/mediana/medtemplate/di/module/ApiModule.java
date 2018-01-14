package com.mediana.medtemplate.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mediana.medtemplate.BuildConfig;
import com.mediana.medtemplate.data.network.NetworkApiService;
import com.mediana.medtemplate.data.network.Url;
import com.mediana.medtemplate.di.scope.ApplicationScope;
import com.mediana.medtemplate.subscription.mci.MCIApiService;
import com.mediana.medtemplate.subscription.telephony.MCIConstant;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IT-10 on 12/10/2017.
 */
@Module
public class ApiModule {

    @ApplicationScope
    @Provides
    OkHttpClient provideClient()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(45, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @ApplicationScope
    @Provides
    public Retrofit provideRetrofit(String baseURL,OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @ApplicationScope
    @Provides
    public NetworkApiService provideApiService() {
        return provideRetrofit(Url.BASE_URL, provideClient()).create(NetworkApiService.class);
    }

    @ApplicationScope
    @Provides
    public MCIApiService provideMCIApiService() {
        return provideRetrofit(MCIConstant.API_ROOT,provideClient()).create(MCIApiService.class);
    }
}
