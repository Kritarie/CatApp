package com.example.samplearchitecture.persistence;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.persistence.preferences.DataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class PersistenceModule {

    private static final String SHARED_PREFS = "ArchitectureSharedPrefs";

    @Provides @NonNull
    public SharedPreferences providePreferences(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    @Provides @NonNull @Singleton
    public ContentResolver provideResolver(@NonNull Context context) {
        return context.getContentResolver();
    }

    @Provides @NonNull @Singleton
    public DataStore provideDataStore(@NonNull SharedPreferences preferences) {
        return new DataStore(preferences);
    }

}
