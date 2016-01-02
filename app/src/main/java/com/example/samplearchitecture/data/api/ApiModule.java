package com.example.samplearchitecture.data.api;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.ApplicationScope;
import com.example.samplearchitecture.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import me.tatarka.parsnip.ParsnipConverterFactory;
import me.tatarka.parsnip.Xml;
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

    @Provides @NonNull @ApplicationScope
    public ArchitectureUrl provideBaseUrl() {
        return baseUrl;
    }

    @Provides @NonNull @ApplicationScope
    public RestClient provideCatApi(@NonNull OkHttpClient okHttpClient, @NonNull Xml xml, @NonNull ArchitectureUrl baseUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(ParsnipConverterFactory.create(xml))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        // Fail early: check Retrofit configuration at creation time
        if (BuildConfig.DEBUG) {
            builder.validateEagerly();
        }

        return builder.build().create(RestClient.class);
    }

}
