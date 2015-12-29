package com.example.samplearchitecture.ui.home;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.api.entities.CatImage;
import com.example.samplearchitecture.ui.DataManager;
import com.example.samplearchitecture.ui.Presenter;

import java.util.List;

import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.Subscriptions;

/**
 * Created by seanamos on 12/28/15.
 */
public class HomePresenter extends Presenter<HomeView> {

    @NonNull
    private BehaviorSubject<List<CatImage>> subject = BehaviorSubject.create();
    @NonNull
    private Subscription subscription = Subscriptions.unsubscribed();

    public HomePresenter(@NonNull DataManager dataManager) {
        super(dataManager);
        dataManager.fetchCatImages().subscribe(subject);
    }

    @Override
    protected void onViewAttached(@NonNull HomeView view) {
        subscription = subject.subscribe(view::showContent, view::showError);
    }

    @Override
    protected void onViewDetacted() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void retry() {
        getDataManager().fetchCatImages().subscribe(subject);
    }
}
