package com.example.samplearchitecture.data;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.RestClient;
import com.example.samplearchitecture.data.api.entity.CatImage;
import com.example.samplearchitecture.data.model.CatModel;
import com.example.samplearchitecture.network.NetworkManager;
import com.example.samplearchitecture.data.persistence.db.CatItem;
import com.example.samplearchitecture.data.persistence.preferences.DataStore;
import com.example.samplearchitecture.util.Converter;
import com.example.samplearchitecture.util.Converters;
import com.example.samplearchitecture.util.DbUtil;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

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

    @Inject
    public DataManager(@NonNull DataStore store, @NonNull RestClient api, @NonNull BriteDatabase db, @NonNull NetworkManager network) {
        this.store = store;
        this.api = api;
        this.db = db;
        this.network = network;
    }

    @NonNull
    public Observable<List<CatModel>> fetchCatImages() {
        return Observable.concat(fetchCatImagesOffline(), fetchCatImagesOnline());
    }

    @NonNull
    public <T> Observable<T> fetchCatImages(Converter<List<CatModel>, T> converter) {
        return fetchCatImages().map(converter::convert);
    }

    @NonNull
    public Observable<List<CatModel>> fetchCatImagesOnline() {
        if (!network.online()) return Observable.empty();
        return api.fetchCats(80)
                .map(catResponse -> catResponse.catData.images)
                .map(catImages -> Converters.convertList(catImages, CatImage.MODEL_CONVERTER))
                .doOnNext(this::persist);
    }

    @NonNull
    public Observable<List<CatModel>> fetchCatImagesOffline() {
        return db.createQuery(CatItem.TABLE, null)
                .mapToList(CatItem.CURSOR_CONVERTER::convert)
                .take(1);
    }

    private void persist(@NonNull List<CatModel> models) {
        int count = models.size();
        if (count == 0) return;
        List<ContentValues> values = Converters.convertList(models, CatModel.VALUE_CONVERTER);
        DbUtil.insert(db, CatItem.TABLE, values);
    }

}
