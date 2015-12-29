package com.example.samplearchitecture;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.api.ApiModule;
import com.example.samplearchitecture.api.RestClient;
import com.example.samplearchitecture.persistence.PersistenceModule;
import com.example.samplearchitecture.network.NetworkModule;
import com.example.samplearchitecture.ui.DataManager;
import com.example.samplearchitecture.ui.home.HomeComponent;
import com.example.samplearchitecture.ui.home.HomeModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by seanamos on 12/28/15.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ApiModule.class,
        PersistenceModule.class
})
public interface ApplicationComponent {

    // Provide API to tests without needing to inject
    @NonNull
    RestClient restClient();

    // Provide DataManager to tests without needing to inject
    @NonNull
    DataManager dataManager();

    void inject(@NonNull ArchitectureApplication app);

    @NonNull
    HomeComponent plus(@NonNull HomeModule homeModule);

}
