package com.mediana.medtemplate.di.module;

import android.app.Application;
import android.content.Context;

import com.mediana.medtemplate.di.quantifier.ActivityContext;
import com.mediana.medtemplate.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IT-10 on 12/9/2017.
 */

@Module
public class AppModule {

    @Provides
    @ApplicationScope
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}
