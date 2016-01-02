package com.example.samplearchitecture;

import android.app.Application;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.data.DataManager;
import com.example.samplearchitecture.data.api.RestClient;
import com.example.samplearchitecture.data.persistence.preferences.DataStore;
import com.example.samplearchitecture.network.NetworkManager;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Named;

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
    private final Application app;

    public ApplicationModule(@NonNull Application app) {
        this.app = app;
    }

    @Provides @NonNull @ApplicationScope
    public Application provideApplication() {
        return app;
    }

    @Provides @NonNull @ApplicationScope
    public Resources provideResources(@NonNull Application app) {
        return app.getResources();
    }

    @Provides @NonNull @ApplicationScope
    public Xml provideXml() {
        return new Xml.Builder().build();
    }

    @Provides @NonNull @Named(MAIN_THREAD_HANDLER) @ApplicationScope
    public Handler provideMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides @NonNull @ApplicationScope
    public Picasso providePicasso(@NonNull Application app, @NonNull OkHttpClient okHttpClient) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

    @Provides @NonNull @ApplicationScope
    public DataManager provideDataManager(@NonNull DataStore store, @NonNull RestClient api,
                                          @NonNull BriteDatabase db, @NonNull NetworkManager network) {
        return new DataManager(store, api, db, network);
    }

}
