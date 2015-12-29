package com.example.samplearchitecture.network;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.BuildConfig;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

import static com.squareup.okhttp.logging.HttpLoggingInterceptor.Level.BODY;
import static com.squareup.okhttp.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class NetworkModule {

    @Provides @NonNull @Singleton
    public OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(httpLoggingInterceptor);
        return okHttpClient;
    }

    @Provides
    @NonNull
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return httpLoggingInterceptor;
    }

}
