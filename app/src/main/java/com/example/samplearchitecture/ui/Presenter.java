package com.example.samplearchitecture.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.samplearchitecture.ui.common.MvpView;

/**
 * Created by seanamos on 12/28/15.
 */
public abstract class Presenter<T extends MvpView> {

    @NonNull
    private final DataManager dataManager;
    @Nullable
    private T view;

    public Presenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @NonNull
    protected final DataManager getDataManager() {
        return dataManager;
    }

    @Nullable
    public final T getView() {
        return view;
    }

    public final void setView(@Nullable T view) {
        this.view = view;
        if (view == null) {
            onViewDetacted();
        } else {
            onViewAttached(view);
        }
    }

    protected void onViewAttached(@NonNull T View) {
        // No Impl
    }

    protected void onViewDetacted() {
        // No Impl
    }

}
