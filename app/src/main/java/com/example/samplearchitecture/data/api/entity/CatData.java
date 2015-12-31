package com.example.samplearchitecture.data.api.entity;

import java.util.List;

import me.tatarka.parsnip.annotations.SerializedName;
import me.tatarka.parsnip.annotations.Tag;

/**
 * Created by Sean on 12/28/2015.
 */
public class CatData {

    @Tag
    @SerializedName("images")
    public List<CatImage> images;

}
