package com.example.samplearchitecture;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.ApiModule;

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
        applicationComponent = prepareApplicationComponent().build();
        applicationComponent.inject(this);
    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                // This url may be changed dynamically for tests! See ArchitectureUrl.
                .apiModule(new ApiModule("http://thecatapi.com/api/"));
    }

    @NonNull
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }
}
