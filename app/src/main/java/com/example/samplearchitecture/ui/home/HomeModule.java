package com.example.samplearchitecture.ui.home;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.ui.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class HomeModule {

    @Provides @NonNull
    public HomePresenter provideHomePresenter(DataManager dataManager) {
        return new HomePresenter(dataManager);
    }

}
