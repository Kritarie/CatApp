package com.example.samplearchitecture.rules;

import android.support.test.espresso.Espresso;

import com.example.samplearchitecture.MockCatApplication;
import com.example.samplearchitecture.util.ThreadPoolIdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Sean on 1/3/2016.
 */
public class ThreadPoolResourceRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final ThreadPoolIdlingResource resource = new ThreadPoolIdlingResource(MockCatApplication.TPE);

                try {
                    Espresso.registerIdlingResources(resource);
                    base.evaluate();
                } finally {
                    Espresso.unregisterIdlingResources(resource);
                }
            }
        };
    }

}
