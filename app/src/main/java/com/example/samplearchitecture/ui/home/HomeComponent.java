package com.example.samplearchitecture.ui.home;

import android.support.annotation.NonNull;

import dagger.Subcomponent;

/**
 * Created by seanamos on 12/28/15.
 */
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {
    void inject(@NonNull HomeActivity activity);
}
