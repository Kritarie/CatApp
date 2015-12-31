package com.example.samplearchitecture.data.persistence.preferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by seanamos on 12/28/15.
 */
public class DataStore {

    @NonNull
    private SharedPreferences preferences;

    public DataStore(@NonNull SharedPreferences preferences) {
        this.preferences = preferences;
    }

    //Fill with preference methods

}
