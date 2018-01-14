package com.mediana.medtemplate.di.module;

import android.support.v4.app.FragmentManager;

import com.mediana.medtemplate.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IT-10 on 1/9/2018.
 */
@Module
class BaseActivityModule {
    @Provides
    FragmentManager provideFragmentManager(BaseActivity baseActivity) {
        return baseActivity.getSupportFragmentManager();
    }
}
