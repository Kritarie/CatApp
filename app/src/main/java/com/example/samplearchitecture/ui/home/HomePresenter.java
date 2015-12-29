package com.example.samplearchitecture.ui.home;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.ui.DataManager;
import com.example.samplearchitecture.ui.Presenter;

/**
 * Created by seanamos on 12/28/15.
 */
public class HomePresenter extends Presenter<HomeView> {

    public HomePresenter(@NonNull DataManager dataManager) {
        super(dataManager);
    }

}
