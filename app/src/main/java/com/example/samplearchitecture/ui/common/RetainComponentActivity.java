package com.example.samplearchitecture.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by seanamos on 12/29/15.
 */
public abstract class RetainComponentActivity<T> extends BaseActivity {

    private static final int COMPONENT_ID = 0xDDD100;

    private T component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = getRetainState().retain(COMPONENT_ID, this::buildComponent);
    }

    protected abstract T buildComponent();

    protected T getComponent() {
        return component;
    }
}
