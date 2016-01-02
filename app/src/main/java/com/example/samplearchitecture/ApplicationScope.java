package com.example.samplearchitecture;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Sean on 1/1/2016.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ApplicationScope {
}
