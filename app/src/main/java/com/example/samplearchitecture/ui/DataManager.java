package com.example.samplearchitecture.ui;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.api.RestClient;
import com.example.samplearchitecture.persistence.preferences.DataStore;

import javax.inject.Inject;

/**
 * Created by seanamos on 12/28/15.
 */
public class DataManager {

    @NonNull
    private DataStore dataStore;
    @NonNull
    private RestClient api;
    @NonNull
    private ContentResolver resolver;

    @Inject
    public DataManager(@NonNull DataStore dataStore, @NonNull RestClient api, @NonNull ContentResolver resolver) {
        this.dataStore = dataStore;
        this.api = api;
        this.resolver = resolver;
    }

    // Fill with methods that return Observable<Thing>

}
