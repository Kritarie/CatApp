package com.example.samplearchitecture.ui.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.samplearchitecture.R;
import com.example.samplearchitecture.ui.common.util.RetainState;

import butterknife.ButterKnife;

/**
 * Created by seanamos on 12/28/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements RetainState.Provider {

    @Nullable
    private Toolbar toolbar;
    @SuppressWarnings("NullableProblems") // Initialized in onCreate
    @NonNull
    private RetainState retainState;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setupToolbar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        retainState = new RetainState(getLastCustomNonConfigurationInstance());
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return retainState.getState();
    }

    @Override
    @NonNull
    public RetainState getRetainState() {
        return retainState;
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Nullable
    protected Toolbar toolbar() {
        return toolbar;
    }
}
