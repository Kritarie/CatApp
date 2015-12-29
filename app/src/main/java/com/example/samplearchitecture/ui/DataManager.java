package com.example.samplearchitecture.ui;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.api.RestClient;
import com.example.samplearchitecture.api.entities.CatImage;
import com.example.samplearchitecture.persistence.preferences.DataStore;
import com.example.samplearchitecture.ui.common.util.Converter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static rx.Observable.Transformer;

/**
 * Created by seanamos on 12/28/15.
 */
public class DataManager {

    final Transformer<?, ?> schedule =
            observable -> observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked") // Schedule will always be type agnostic
    <T> Transformer<T, T> applySchedulers() {
        return (Transformer<T, T>) schedule;
    }

    @NonNull
    private DataStore dataStore;
    @NonNull
    private RestClient api;
    @NonNull
    private ContentResolver resolver;

    @Inject
    public DataManager(@NonNull DataStore dataStore, @NonNull RestClient api, @NonNull ContentResolver resolver) {
        this.dataStore = dataStore;
        this.api = api;
        this.resolver = resolver;
    }

    public Observable<List<CatImage>> fetchCatImages() {
        return api.fetchCats(80)
                .map(catResponse -> catResponse.catData.images)
                .compose(applySchedulers());
    }

    public <T> Observable<T> fetchCatImages(Converter<List<CatImage>, T> converter) {
        return fetchCatImages()
                .map(converter::convert)
                .compose(applySchedulers());
    }

}
