package com.example.samplearchitecture.data.api;

import android.support.annotation.NonNull;

import com.squareup.okhttp.HttpUrl;

import java.util.concurrent.atomic.AtomicReference;

import retrofit.BaseUrl;

/**
 * Created by seanamos on 12/28/15.
 */
public class ArchitectureUrl implements BaseUrl {

    @NonNull
    private final AtomicReference<HttpUrl> baseUrl;

    public ArchitectureUrl(@NonNull String baseUrl) {
        this.baseUrl = new AtomicReference<>(HttpUrl.parse(baseUrl));
    }

    public void setBaseUrl(@NonNull String baseUrl) {
        this.baseUrl.set(HttpUrl.parse(baseUrl));
    }

    @Override
    public HttpUrl url() {
        return baseUrl.get();
    }
}
