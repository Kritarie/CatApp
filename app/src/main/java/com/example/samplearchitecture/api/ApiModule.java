package com.example.samplearchitecture.api;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class ApiModule {

    @NonNull
    private final ArchitectureUrl baseUrl;

    public ApiModule(@NonNull String baseUrl) {
        this.baseUrl = new ArchitectureUrl(baseUrl);
    }

    @Provides @NonNull @Singleton
    public ArchitectureUrl provideChangeableBaseUrl() {
        return baseUrl;
    }

    @Provides @NonNull @Singleton
    public RestClient provideQualityMattersApi(@NonNull OkHttpClient okHttpClient, @NonNull ArchitectureUrl baseUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        // Fail early: check Retrofit configuration at creation time
        if (BuildConfig.DEBUG) {
            builder.validateEagerly();
        }

        return builder.build().create(RestClient.class);
    }

}
