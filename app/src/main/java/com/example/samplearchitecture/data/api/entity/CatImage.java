package com.example.samplearchitecture.data.api.entity;

import me.tatarka.parsnip.annotations.SerializedName;
import me.tatarka.parsnip.annotations.Tag;

/**
 * Created by Sean on 12/28/2015.
 */
public class CatImage {

    @Tag
    @SerializedName("url")
    public String imageUrl;

    @Tag
    @SerializedName("id")
    public String id;

    @Tag
    @SerializedName("source_url")
    public String sourceUrl;

}
