package com.example.samplearchitecture.api.entities;

import me.tatarka.parsnip.annotations.SerializedName;
import me.tatarka.parsnip.annotations.Tag;

/**
 * Created by Sean on 12/28/2015.
 */
public class CatImage {

    @Tag
    @SerializedName("url")
    String imageUrl;

    @Tag
    @SerializedName("id")
    String id;

    @Tag
    @SerializedName("source_url")
    String sourceUrl;

}
