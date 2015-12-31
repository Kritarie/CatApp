package com.example.samplearchitecture.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;

import com.example.samplearchitecture.CatApplication;
import com.example.samplearchitecture.R;
import com.example.samplearchitecture.ui.common.RetainComponentActivity;
import com.example.samplearchitecture.ui.home.di.HomeComponent;
import com.example.samplearchitecture.ui.home.di.HomeModule;
import com.example.samplearchitecture.ui.home.mvp.HomeView;
import com.example.samplearchitecture.ui.home.recycler.HomeItem;

import java.util.List;

import butterknife.Bind;

public class HomeActivity extends RetainComponentActivity<HomeComponent> implements HomeView {

    @Bind(R.id.recycler)
    RecyclerView recycler;

    @Bind(R.id.progress)
    ContentLoadingProgressBar progress;

    @Override
    protected HomeComponent buildComponent() {
        return CatApplication.get(this).applicationComponent().plus(new HomeModule());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getComponent().presenter().attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getComponent().presenter().detachView();
    }

    @Override
    public void showLoading(boolean loading) {
        if (loading) {
            progress.show();
        } else {
            progress.hide();
        }
    }

    @Override
    public void showError(@NonNull Throwable error) {
        progress.hide();
    }

    @Override
    public void showContent(@NonNull List<HomeItem<?>> items) {
        progress.hide();
    }
}
