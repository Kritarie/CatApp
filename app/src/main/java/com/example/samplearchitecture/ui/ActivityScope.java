package com.example.samplearchitecture.ui;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by seanamos on 12/29/15.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
