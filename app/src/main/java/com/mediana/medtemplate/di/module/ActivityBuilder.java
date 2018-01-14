package com.mediana.medtemplate.di.module;

import com.mediana.medtemplate.FloatingActionActivity;
import com.mediana.medtemplate.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We map all our activities here.
 * And Dagger know our activities in compile time.
 * In our app we have Main and Detail activity. So we map both activities here.
 */
@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules ={MainActivityModule.class,BaseActivityModule.class})
    abstract FloatingActionActivity bindMainActivity();

//    @ContributesAndroidInjector(modules = MainActivityModule.class)
//    abstract FloatingActionActivity bindMainActivity();
}
