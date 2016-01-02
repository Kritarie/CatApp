package com.example.samplearchitecture.ui.home.di;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.ui.ActivityScope;
import com.example.samplearchitecture.data.DataManager;
import com.example.samplearchitecture.ui.home.mvp.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class HomeModule {

    @Provides @ActivityScope @NonNull
    public HomePresenter provideHomePresenter(@NonNull DataManager dataManager) {
        return new HomePresenter(dataManager);
    }

}
