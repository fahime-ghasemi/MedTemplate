package com.mediana.medtemplate.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by IT-10 on 12/11/2017.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}