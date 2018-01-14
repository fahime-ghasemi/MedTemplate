package com.mediana.medtemplate.di.component;

import com.mediana.medtemplate.MedTemplateApplication;
import com.mediana.medtemplate.di.module.AppModule;
import com.mediana.medtemplate.di.module.ActivityBuilder;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by IT-10 on 12/9/2017.
 */

/*****
 * you need to create ActivityBuilder and appmodule for all projects
 */
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<MedTemplateApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MedTemplateApplication> {}
}