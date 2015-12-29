package com.example.samplearchitecture;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.tatarka.parsnip.Xml;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class ApplicationModule {

    public static final String MAIN_THREAD_HANDLER = "main_thread_handler";

    @NonNull
    private final CatApplication app;

    public ApplicationModule(@NonNull CatApplication app) {
        this.app = app;
    }

    @Provides @NonNull @Singleton
    public CatApplication provideApplication() {
        return app;
    }

    @Provides @NonNull
    public Context provideApplicationContext() {
        return app.getApplicationContext();
    }

    @Provides @NonNull
    public Resources provideResources() {
        return app.getResources();
    }

    @Provides @NonNull @Singleton
    public Xml provideXml() {
        return new Xml.Builder().build();
    }

    @Provides @NonNull @Named(MAIN_THREAD_HANDLER) @Singleton
    public Handler provideMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides @NonNull @Singleton
    public Picasso providePicasso(@NonNull CatApplication app, @NonNull OkHttpClient okHttpClient) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

}
