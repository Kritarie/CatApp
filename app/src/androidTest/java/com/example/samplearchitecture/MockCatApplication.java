package com.example.samplearchitecture;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.ApiModule;
import com.example.samplearchitecture.data.persistence.PersistenceModule;
import com.example.samplearchitecture.network.NetworkModule;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by Sean on 1/2/2016.
 */
public class MockCatApplication extends CatApplication {

    public static final ThreadPoolExecutor TPE = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.getInstance().registerSchedulersHook(new TestSchedulerHook());
    }

    @NonNull
    @Override
    protected ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(""))
                .networkModule(new NetworkModule())
                .persistenceModule(new PersistenceModule())
                .build();
    }

    private class TestSchedulerHook extends RxJavaSchedulersHook {

        @Override
        public Scheduler getComputationScheduler() {
            return Schedulers.from(TPE);
        }

        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.from(TPE);
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.from(TPE);
        }
    }
}
