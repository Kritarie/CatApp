package com.example.samplearchitecture.data.api.entity;

import com.example.samplearchitecture.data.model.CatModel;
import com.example.samplearchitecture.util.Converter;

import me.tatarka.parsnip.annotations.SerializedName;
import me.tatarka.parsnip.annotations.Tag;

/**
 * Created by Sean on 12/28/2015.
 */
public class CatImage implements CatModel {

    @Tag
    @SerializedName("url")
    String imageUrl;

    @Tag
    @SerializedName("id")
    String id;

    @Tag
    @SerializedName("source_url")
    String sourceUrl;

    public static final Converter<CatImage, CatModel> MODEL_CONVERTER = entity -> entity;

    @Override
    public String id() {
        return id;
    }

    @Override
    public String imageUrl() {
        return imageUrl;
    }

    @Override
    public String sourceUrl() {
        return sourceUrl;
    }
}
