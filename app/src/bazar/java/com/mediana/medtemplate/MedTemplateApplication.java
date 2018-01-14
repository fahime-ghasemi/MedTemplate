package com.mediana.medtemplate;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by IT-10 on 11/28/2017.
 */

public class MedTemplateApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();

//        DaggerAppComponent
//                .builder()
//                .application(this)
//                .build()
//                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
