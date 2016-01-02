package com.example.samplearchitecture.data.api.entity;

import java.util.List;

import me.tatarka.parsnip.annotations.SerializedName;

/**
 * Created by Sean on 12/28/2015.
 */
@SerializedName("response")
public class CatResponse {

    @SerializedName("data")
    public CatData data;

    public static class CatData {
        @SerializedName("images")
        public CatImages images;
    }

    public static class CatImages {
        @SerializedName("image")
        public List<CatImage> images;
    }

}
