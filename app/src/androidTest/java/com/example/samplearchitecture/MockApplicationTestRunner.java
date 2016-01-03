package com.example.samplearchitecture;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.example.samplearchitecture.util.RxIdlingResource;

import rx.plugins.RxJavaPlugins;

/**
 * Created by Sean on 1/2/2016.
 */
public class MockApplicationTestRunner extends AndroidJUnitRunner {

    @Override
    public void onCreate(Bundle arguments) {
        RxJavaPlugins.getInstance().registerObservableExecutionHook(RxIdlingResource.get());
        super.onCreate(arguments);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockCatApplication.class.getName(), context);
    }
}