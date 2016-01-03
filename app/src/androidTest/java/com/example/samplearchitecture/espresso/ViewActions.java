package com.example.samplearchitecture.espresso;

import android.support.annotation.NonNull;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by Sean on 1/2/2016.
 */
public class ViewActions {

    @NonNull
    public static ViewAction noOp() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "no-op";
            }

            @Override
            public void perform(UiController uiController, View view) {
                // no-op
            }
        };
    }

    private ViewActions() {
        throw new AssertionError("No instances.");
    }

}
