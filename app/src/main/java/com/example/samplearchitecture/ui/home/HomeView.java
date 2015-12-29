package com.example.samplearchitecture.ui.home;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.ui.common.MvpView;

import java.util.List;

/**
 * Created by seanamos on 12/28/15.
 */
public interface HomeView extends MvpView {

    void showLoading(boolean loading);

    void showError(@NonNull Throwable error);

    void showContent(@NonNull List<HomeItem> items);
}
