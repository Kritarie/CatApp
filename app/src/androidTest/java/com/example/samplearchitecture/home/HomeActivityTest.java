package com.example.samplearchitecture.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.samplearchitecture.rules.MockWebServerRule;
import com.example.samplearchitecture.rules.RxResourceRule;
import com.example.samplearchitecture.ui.home.HomeActivity;

import org.junit.Rule;
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
            .around(new RxResourceRule())
            .around(new ActivityTestRule<>(HomeActivity.class));
}
