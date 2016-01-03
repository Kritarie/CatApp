package com.example.samplearchitecture;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.ApiModule;
import com.example.samplearchitecture.data.persistence.PersistenceModule;
import com.example.samplearchitecture.network.NetworkModule;

/**
 * Created by Sean on 1/2/2016.
 */
public class MockCatApplication extends CatApplication {

    @NonNull
    @Override
    public ApplicationComponent applicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(""))
                .networkModule(new NetworkModule())
                .persistenceModule(new PersistenceModule())
                .build();
    }
}
