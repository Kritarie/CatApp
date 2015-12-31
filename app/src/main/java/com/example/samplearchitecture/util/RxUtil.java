package com.example.samplearchitecture.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sean on 12/30/2015.
 */
public final class RxUtil {

    private static final Observable.Transformer<?, ?> schedule =
            observable -> observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked") // Schedule will always be type agnostic
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedule;
    }

    private RxUtil() {
        throw new AssertionError("No instances.");
    }

}
