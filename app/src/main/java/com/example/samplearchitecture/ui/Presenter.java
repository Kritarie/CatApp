package com.example.samplearchitecture.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.samplearchitecture.ui.common.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by seanamos on 12/28/15.
 */
public abstract class Presenter<T extends MvpView> {

    @NonNull
    private final DataManager dataManager;
    @NonNull
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    @Nullable
    private T view;

    public Presenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @NonNull
    protected final DataManager getDataManager() {
        return dataManager;
    }

    @NonNull
    protected final CompositeSubscription getSubscriptions() {
        return compositeSubscription;
    }

    @Nullable
    protected final T getView() {
        return view;
    }

    public final void attachView(@NonNull T view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
        onViewAttached(view);
    }

    public final void detachView() {
        view = null;
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        onViewDetacted();
    }

    protected void onViewAttached(@NonNull T View) {
        // No Impl
    }

    protected void onViewDetacted() {
        // No Impl
    }

}
