package com.example.samplearchitecture.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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

public class HomeActivity extends RetainComponentActivity<HomeComponent> implements HomeView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipe;

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
        setupRecycler();
        setupSwipeToRefresh();
        getComponent().presenter().attachView(this);
    }

    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //TODO set empty adapter
    }

    private void setupSwipeToRefresh() {
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getComponent().presenter().detachView();
    }

    /* HomeView Impl */

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

    /* SwipeToRefresh Impl */

    @Override
    public void onRefresh() {
        getComponent().presenter().refresh();
    }

}
