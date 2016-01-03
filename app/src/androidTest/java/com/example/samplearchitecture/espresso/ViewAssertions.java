package com.example.samplearchitecture.espresso;

import android.support.annotation.NonNull;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * Created by Sean on 1/2/2016.
 */
public class ViewAssertions {

    @NonNull
    public static ViewAssertion recyclerViewShouldHaveItemsCount(int count) {
        return (view, noViewFoundException) -> {
            final RecyclerView recyclerView = (RecyclerView) view;
            final int actualCount = recyclerView.getAdapter().getItemCount();

            if (actualCount != count) {
                throw new AssertionError("RecyclerView has " + actualCount + " while expected " + count);
            }
        };
    }

    @NonNull
    public static ViewAssertion toolbarShouldHaveTitle(CharSequence title) {
        return (view, noViewFoundException) -> {
            final Toolbar toolbar = (Toolbar) view;
            final CharSequence actualTitle = toolbar.getTitle();

            if (!actualTitle.equals(title)) {
                throw new AssertionError("Toolbar title is " + actualTitle + " while expected " + title);
            }
        };
    }

    private ViewAssertions() {
        throw new AssertionError("No instances.");
    }

}
