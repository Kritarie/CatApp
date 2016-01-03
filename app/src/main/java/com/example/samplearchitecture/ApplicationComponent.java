package com.example.samplearchitecture;

import com.example.samplearchitecture.data.api.ApiModule;
import com.example.samplearchitecture.data.api.ArchitectureUrl;
import com.example.samplearchitecture.data.api.RestClient;
import com.example.samplearchitecture.data.persistence.PersistenceModule;
import com.example.samplearchitecture.network.NetworkModule;
import com.example.samplearchitecture.data.DataManager;
import com.example.samplearchitecture.ui.home.di.HomeComponent;
import com.example.samplearchitecture.ui.home.di.HomeModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by seanamos on 12/28/15.
 */
@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ApiModule.class,
        PersistenceModule.class
})
public interface ApplicationComponent {
    // Provide direct for Tests
    ArchitectureUrl architectureUrl();

    void inject(CatApplication app);
    HomeComponent plus(HomeModule homeModule);
}
