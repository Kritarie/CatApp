package com.example.samplearchitecture.ui.home.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.samplearchitecture.data.DataManager;
import com.example.samplearchitecture.data.model.CatModel;
import com.example.samplearchitecture.ui.Presenter;
import com.example.samplearchitecture.ui.home.recycler.HomeItem;
import com.example.samplearchitecture.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * Created by seanamos on 12/28/15.
 */
public class HomePresenter extends Presenter<HomeView> {

    @NonNull
    private Observable<List<HomeItem<?>>> observable;

    @Nullable
    private List<HomeItem<?>> data;
    @Nullable
    private Throwable error;

    public HomePresenter(@NonNull DataManager dataManager) {
        super(dataManager);
        observable = getDataManager()
                .fetchCatImages(this::toHomeItems)
                .compose(RxUtil.applySchedulers());
    }

    public void refresh() {
        clearSubscriptions();
        observable = getDataManager()
                .fetchCatImages(this::toHomeItems)
                .compose(RxUtil.applySchedulers());
    }

    @Override
    protected void onViewAttached(@NonNull HomeView view) {
        Subscription sub = observable.subscribe(view::showContent, view::showError);
        getSubscriptions().add(sub);
    }

    public List<HomeItem<?>> toHomeItems(List<CatModel> entity) {
        int count = entity.size();
        ArrayList<HomeItem<?>> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(new HomeItem<>(entity.get(i)));
        }
        return list;
    }
}
