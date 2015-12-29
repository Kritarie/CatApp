package com.example.samplearchitecture.ui.home.di;

import com.example.samplearchitecture.ui.ActivityScope;
import com.example.samplearchitecture.ui.home.HomeActivity;
import com.example.samplearchitecture.ui.home.mvp.HomePresenter;

import dagger.Subcomponent;

/**
 * Created by seanamos on 12/28/15.
 */
@ActivityScope
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {
    HomePresenter presenter();
    void inject(HomeActivity activity);
}
