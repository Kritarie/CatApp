package com.example.samplearchitecture.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;

import com.example.samplearchitecture.CatApplication;
import com.example.samplearchitecture.R;
import com.example.samplearchitecture.api.entities.CatImage;
import com.example.samplearchitecture.ui.common.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    HomePresenter presenter;

    @Bind(R.id.recycler)
    RecyclerView recycker;

    @Bind(R.id.progress)
    ContentLoadingProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = (HomePresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            CatApplication.get(this).applicationComponent().plus(new HomeModule()).inject(this);
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void showLoading(boolean loading) {
        //TODO
    }

    @Override
    public void showError(@NonNull Throwable error) {
        progress.hide();
    }

    @Override
    public void showContent(@NonNull List<CatImage> items) {
        progress.hide();
    }
}
