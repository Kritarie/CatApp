package com.example.samplearchitecture.util;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.example.samplearchitecture.CatApplication;

/**
 * Created by Sean on 1/2/2016.
 */
public final class TestUtil {

    @NonNull
    public static CatApplication app() {
        return (CatApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }

    private TestUtil() {
        throw new AssertionError("No instances.");
    }

}
