package com.example.samplearchitecture.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sean on 12/29/2015.
 */
public final class Converters {

    @NonNull
    public static <T, R> List<R> convertList(@NonNull List<T> inList, @NonNull Converter<T, R> converter) {
        int count = inList.size();
        if (count == 0) return Collections.emptyList();
        ArrayList<R> outList = new ArrayList<>(inList.size());
        for (int i = 0; i < count; i++) {
            T in = inList.get(i);
            R out = converter.convert(in);
            outList.add(out);
        }
        return outList;
    }

    private Converters() {
        throw new AssertionError("No instances");
    }

}
