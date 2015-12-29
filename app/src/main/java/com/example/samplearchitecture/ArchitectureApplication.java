package com.example.samplearchitecture;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.api.ApiModule;

import timber.log.Timber;

/**
 * Created by seanamos on 12/28/15.
 */
public class ArchitectureApplication extends Application {

    @SuppressWarnings("NullableProblems") // Initialized in onCreate
    @NonNull
    private ApplicationComponent applicationComponent;

    @NonNull
    public static ArchitectureApplication get(@NonNull Context context) {
        return (ArchitectureApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = prepareApplicationComponent().build();
        applicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                        // This url may be changed dynamically for tests! See ArchitectureUrl.
                .apiModule(new ApiModule("https://raw.githubusercontent.com/artem-zinnatullin/qualitymatters/master/rest_api/"));
    }

    @NonNull
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }
}
