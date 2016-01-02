package com.example.samplearchitecture.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Sean on 12/30/2015.
 */
public final class RxUtil {

    private static final Observable.Transformer<?, ?> schedule =
            observable -> observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked") // Schedule will always be type agnostic
    public static <T> Observable.Transformer<T, T> schedule() {
        return (Observable.Transformer<T, T>) schedule;
    }

    @SuppressWarnings("unchecked") // Schedule will always be type agnostic
    public static <T> Func2<List<T>, List<T>, List<T>> accumulate() {
        return RxUtil::combine;
    }

    @NonNull
    private static <T> List<T> combine(@Nullable List<T> a, @Nullable List<T> b) {
        if (a == null || a.isEmpty()) {
            if (b == null || b.isEmpty()) {
                return Collections.emptyList();
            } else {
                return b;
            }
        } else if (b == null || b.isEmpty()) {
            return a;
        } else {
            List<T> c = new ArrayList<>(a.size() + b.size());
            c.addAll(a);
            c.addAll(b);
            return c;
        }
    }

    private RxUtil() {
        throw new AssertionError("No instances.");
    }

}
