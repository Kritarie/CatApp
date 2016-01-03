package com.example.samplearchitecture.home;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Sean on 1/3/2016.
 */
public class HomeScreen {

    @NonNull
    public HomeScreen shouldDisplayTitle(@NonNull String title) {
        onView(allOf(withText(title), withParent(withId(R.id.toolbar)))).check(matches(isDisplayed()));
        return this;
    }

}
