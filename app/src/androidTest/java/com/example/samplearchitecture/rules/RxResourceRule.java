package com.example.samplearchitecture.rules;

import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;

import com.example.samplearchitecture.util.RxIdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Sean on 1/3/2016.
 */
public class RxResourceRule implements TestRule {

    @Override
    @NonNull
    public Statement apply(@NonNull Statement base, @NonNull Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final RxIdlingResource resource = RxIdlingResource.get();

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
