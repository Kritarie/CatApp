package com.example.samplearchitecture.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.samplearchitecture.ui.common.MvpView;

/**
 * Created by seanamos on 12/28/15.
 */
public abstract class Presenter<T extends MvpView> {

    @NonNull
    private DataManager dataManager;
    @Nullable
    private T view;

    public Presenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @NonNull
    protected DataManager getDataManager() {
        return dataManager;
    }

    @Nullable
    public T getView() {
        return view;
    }

    public void setView(@NonNull T view) {
        this.view = view;
    }

}
