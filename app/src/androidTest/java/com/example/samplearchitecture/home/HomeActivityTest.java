package com.example.samplearchitecture.home;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.samplearchitecture.R;
import com.example.samplearchitecture.rules.MockWebServerRule;
import com.example.samplearchitecture.rules.RxResourceRule;
import com.example.samplearchitecture.rules.ThreadPoolResourceRule;
import com.example.samplearchitecture.ui.home.HomeActivity;
import com.example.samplearchitecture.util.TestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

/**
 * Created by Sean on 1/2/2016.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public RuleChain rules = RuleChain.emptyRuleChain()
            .around(new MockWebServerRule(this))
            .around(new ThreadPoolResourceRule())
            .around(new ActivityTestRule<>(HomeActivity.class));

    @SuppressWarnings("NullableProblems") // Initialized in @Before
    @NonNull
    private HomeScreen homeScreen;

    @Before
    public void beforeEachTest() {
        homeScreen = new HomeScreen();
    }

    @Test
    public void shouldDisplayTitle() {
        homeScreen.shouldDisplayTitle(TestUtil.app().getString(R.string.home_title));
    }
}
