package com.example.samplearchitecture.network;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samplearchitecture.BuildConfig;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.squareup.okhttp.logging.HttpLoggingInterceptor.Level.BODY;
import static com.squareup.okhttp.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class NetworkModule {

    private static final String TAG = "Network_Log";

    @Provides @NonNull @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return httpLoggingInterceptor;
    }

    @Provides @NonNull @Singleton
    public OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(httpLoggingInterceptor);
        return okHttpClient;
    }

    @Provides @NonNull @Singleton
    public NetworkManager provideNetworkManager(@NonNull Application app) {
        return new NetworkManager(app);
    }

}
