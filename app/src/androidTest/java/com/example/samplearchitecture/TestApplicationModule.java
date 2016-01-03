package com.example.samplearchitecture;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sean on 1/3/2016.
 */
@Module
public class TestApplicationModule extends ApplicationModule {

    public TestApplicationModule(@NonNull Application app) {
        super(app);
    }

    @Provides @NonNull @ApplicationScope
    public ThreadPoolExecutor provideExecutor() {
        return new ThreadPoolExecutor(4, 8, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
    }
}
