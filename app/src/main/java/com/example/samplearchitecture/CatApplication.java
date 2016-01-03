package com.example.samplearchitecture;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.ApiModule;
import com.example.samplearchitecture.data.persistence.PersistenceModule;
import com.example.samplearchitecture.network.NetworkModule;

/**
 * Created by seanamos on 12/28/15.
 */
public class CatApplication extends Application {

    @SuppressWarnings("NullableProblems") // Initialized in onCreate
    @NonNull
    private ApplicationComponent applicationComponent;

    @NonNull
    public static CatApplication get(@NonNull Context context) {
        return (CatApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = buildApplicationComponent();
        applicationComponent.inject(this);
    }

    @NonNull
    protected ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule("http://thecatapi.com/api/"))
                .persistenceModule(new PersistenceModule())
                .networkModule(new NetworkModule())
                .build();
    }

    @NonNull
    public final ApplicationComponent applicationComponent() {
        return applicationComponent;
    }
}
