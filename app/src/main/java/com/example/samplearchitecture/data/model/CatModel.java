package com.example.samplearchitecture.data.model;

import android.content.ContentValues;

import com.example.samplearchitecture.data.persistence.db.CatItem;
import com.example.samplearchitecture.util.Converter;

/**
 * Created by Sean on 12/30/2015.
 */
public interface CatModel {
    String id();
    String imageUrl();
    String sourceUrl();

    Converter<CatModel, ContentValues> VALUE_CONVERTER = model ->
            new CatItem.Builder()
                    .id(model.id())
                    .imageUrl(model.imageUrl())
                    .sourceUrl(model.sourceUrl())
                    .build();
}
