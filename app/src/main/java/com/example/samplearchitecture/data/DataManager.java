package com.example.samplearchitecture.data;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.RestClient;
import com.example.samplearchitecture.network.NetworkManager;
import com.example.samplearchitecture.data.persistence.db.CatItem;
import com.example.samplearchitecture.data.persistence.preferences.DataStore;
import com.example.samplearchitecture.util.Converter;
import com.example.samplearchitecture.util.Converters;
import com.example.samplearchitecture.util.DbUtil;
import com.example.samplearchitecture.util.RxUtil;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;

/**
 * Created by seanamos on 12/28/15.
 */
public class DataManager {

    @NonNull
    private DataStore store;
    @NonNull
    private RestClient api;
    @NonNull
    private BriteDatabase db;
    @NonNull
    private NetworkManager network;

    public DataManager(@NonNull DataStore store, @NonNull RestClient api, @NonNull BriteDatabase db, @NonNull NetworkManager network) {
        this.store = store;
        this.api = api;
        this.db = db;
        this.network = network;
    }

    @NonNull
    public Observable<List<CatItem>> fetchCatImages() {
        return Observable.concat(fetchCatImagesOffline(), fetchCatImagesOnline())
                .scan(RxUtil.accumulate());
    }

    @NonNull
    public <T> Observable<T> fetchCatImages(Converter<List<CatItem>, T> converter) {
        return fetchCatImages().map(converter::convert);
    }

    @NonNull
    public Observable<List<CatItem>> fetchCatImagesOnline() {
        if (!network.online()) return Observable.empty();
        return api.fetchCats(10)
                .map(catResponse -> catResponse.data.images.images)
                .map(catImages -> Converters.convertList(catImages, CatItem.RESPONSE_CONVERTER))
                .doOnNext(this::persist);
    }

    @NonNull
    public Observable<List<CatItem>> fetchCatImagesOffline() {
        return db.createQuery(CatItem.TABLE, "SELECT * FROM " + CatItem.TABLE)
                .mapToList(CatItem.CURSOR_CONVERTER::convert)
                .first();
    }

    private void persist(@NonNull List<CatItem> items) {
        int count = items.size();
        if (count == 0) return;
        List<ContentValues> values = Converters.convertList(items, CatItem.VALUES_CONVERTER);
        DbUtil.insert(db, CatItem.TABLE, values);
    }

}
