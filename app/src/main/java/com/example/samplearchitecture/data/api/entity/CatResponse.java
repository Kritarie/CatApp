package com.example.samplearchitecture.data.api.entity;

import com.google.auto.value.AutoValue;

import me.tatarka.parsnip.annotations.SerializedName;
import me.tatarka.parsnip.annotations.Tag;

/**
 * Created by Sean on 12/28/2015.
 */
@AutoValue
public class CatResponse {

    @Tag
    @SerializedName("data")
    public CatData catData;

}
