package com.mediana.medtemplate.di.module;

import android.app.Application;
import android.content.Context;

import com.mediana.medtemplate.BaseActivity;
import com.mediana.medtemplate.di.quantifier.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IT-10 on 12/9/2017.
 */

@Module
public class MainActivityModule {

    @Provides
    String provideMainView(){
        return "ssss";
    }
//
//    @Provides
//    MainPresenter provideMainPresenter(MainView mainView, ApiService apiService){
//        return new MainPresenterImpl(mainView, apiService);
//    }
}
