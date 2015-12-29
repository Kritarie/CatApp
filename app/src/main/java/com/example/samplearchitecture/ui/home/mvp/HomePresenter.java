package com.example.samplearchitecture.ui.home.mvp;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.api.entities.CatImage;
import com.example.samplearchitecture.ui.DataManager;
import com.example.samplearchitecture.ui.Presenter;
import com.example.samplearchitecture.ui.common.util.Converter;
import com.example.samplearchitecture.ui.home.mvp.HomeView;
import com.example.samplearchitecture.ui.home.recycler.HomeItem;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * Created by seanamos on 12/28/15.
 */
public class HomePresenter extends Presenter<HomeView> implements Converter<List<CatImage>, List<HomeItem<?>>> {

    @NonNull
    private BehaviorSubject<List<HomeItem<?>>> subject = BehaviorSubject.create();

    public HomePresenter(@NonNull DataManager dataManager) {
        super(dataManager);
        dataManager.fetchCatImages(this).subscribe(subject);
    }

    @Override
    protected void onViewAttached(@NonNull HomeView view) {
        Subscription sub = subject.subscribe(view::showContent, view::showError);
        getSubscriptions().add(sub);
    }

    public void retry() {
        getDataManager().fetchCatImages(this).subscribe(subject);
    }

    @Override
    public List<HomeItem<?>> convert(List<CatImage> entity) {
        int count = entity.size();
        ArrayList<HomeItem<?>> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(new HomeItem<>(entity.get(i)));
        }
        return list;
    }
}
