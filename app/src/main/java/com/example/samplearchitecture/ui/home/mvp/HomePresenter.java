package com.example.samplearchitecture.ui.home.mvp;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.DataManager;
import com.example.samplearchitecture.data.persistence.db.CatItem;
import com.example.samplearchitecture.ui.common.Presenter;
import com.example.samplearchitecture.ui.home.recycler.HomeItem;
import com.example.samplearchitecture.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.observables.ConnectableObservable;

/**
 * Created by seanamos on 12/28/15.
 */
public class HomePresenter extends Presenter<HomeView> {

    @NonNull
    private ConnectableObservable<List<HomeItem<?>>> observable;

    public HomePresenter(@NonNull DataManager dataManager) {
        super(dataManager);
        observable = load();
        observable.connect();
    }

    private ConnectableObservable<List<HomeItem<?>>> load() {
        return getDataManager()
                .fetchCatImages(this::toHomeItems)
                .compose(RxUtil.schedule())
                .replay(1);
    }

    public void refresh() {
        clearSubscriptions();
        observable = load();
        observable.connect();
        if (getView() != null) {
            Subscription sub = observable.subscribe(getView()::showContent, getView()::showError);
            getSubscriptions().add(sub);
        }
    }

    @Override
    protected void onViewAttached(@NonNull HomeView view) {
        Subscription sub = observable.subscribe(view::showContent, view::showError);
        getSubscriptions().add(sub);
    }

    @NonNull
    public List<HomeItem<?>> toHomeItems(@NonNull List<CatItem> entity) {
        int count = entity.size();
        ArrayList<HomeItem<?>> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(new HomeItem<>(entity.get(i)));
        }
        return list;
    }
}
