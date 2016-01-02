package com.example.samplearchitecture.data.persistence;

import android.app.Application;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.ApplicationScope;
import com.example.samplearchitecture.data.persistence.db.CatOpenHelper;
import com.example.samplearchitecture.data.persistence.preferences.DataStore;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.*;

/**
 * Created by seanamos on 12/28/15.
 */
@Module
public class PersistenceModule {

    private static final String SHARED_PREFS = "ArchitectureSharedPrefs";

    @Provides @NonNull @ApplicationScope
    public ContentResolver provideResolver(@NonNull Application app) {
        return app.getContentResolver();
    }

    @Provides @NonNull @ApplicationScope
    public SharedPreferences providePreferences(@NonNull Application app) {
        return app.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
    }

    @Provides @NonNull @ApplicationScope
    public DataStore provideDataStore(@NonNull SharedPreferences preferences) {
        return new DataStore(preferences);
    }

    @Provides @NonNull @ApplicationScope
    SQLiteOpenHelper provideOpenHelper(@NonNull Application application) {
        return new CatOpenHelper(application);
    }

    @Provides @NonNull @ApplicationScope
    SqlBrite provideSqlBrite() {
        return SqlBrite.create();
    }

    @Provides @NonNull @ApplicationScope
    BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        return sqlBrite.wrapDatabaseHelper(helper);
    }

}
