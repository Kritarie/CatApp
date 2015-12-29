package com.example.samplearchitecture.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.samplearchitecture.ArchitectureApplication;
import com.example.samplearchitecture.R;
import com.example.samplearchitecture.ui.common.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            ArchitectureApplication.get(this).applicationComponent().plus(new HomeModule()).inject(this);
        }
    }

    @Override
    public void showLoading(boolean loading) {
        //TODO
    }

    @Override
    public void showError(@NonNull Throwable error) {
        //TODO
    }

    @Override
    public void showContent(@NonNull List<HomeItem> items) {
        //TODO
    }
}
