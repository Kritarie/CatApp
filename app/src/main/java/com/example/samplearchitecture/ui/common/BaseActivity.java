package com.example.samplearchitecture.ui.common;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.samplearchitecture.R;

import butterknife.ButterKnife;

/**
 * Created by seanamos on 12/28/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    private Toolbar toolbar;

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

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Nullable
    protected Toolbar toolbar() {
        return toolbar;
    }
}
