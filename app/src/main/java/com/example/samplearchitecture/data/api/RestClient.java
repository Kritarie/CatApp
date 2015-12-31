package com.example.samplearchitecture.data.api;


import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.api.entity.CatResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by seanamos on 12/28/15.
 */
public interface RestClient {

    @GET("images/get?format=xml&type=png&size=med") @NonNull
    Observable<CatResponse> fetchCats(@Query("results_per_page") int pageSize);

}
