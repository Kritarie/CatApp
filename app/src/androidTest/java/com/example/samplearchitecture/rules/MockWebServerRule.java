package com.example.samplearchitecture.rules;

import android.support.annotation.NonNull;

import com.example.samplearchitecture.util.TestUtil;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.reflect.Method;

/**
 * Created by Sean on 1/2/2016.
 */
public class MockWebServerRule implements TestRule {

    @NonNull
    private final Object testClassInstance;

    public MockWebServerRule(@NonNull Object testClassInstance) {
        this.testClassInstance = testClassInstance;
    }

    @Override
    @NonNull
    public Statement apply(@NonNull Statement base, @NonNull Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final NeedsMockWebServer needsMockWebServer = description.getAnnotation(NeedsMockWebServer.class);

                if (needsMockWebServer != null) {
                    final MockWebServer mockWebServer = new MockWebServer();
                    mockWebServer.start();

                    TestUtil.app().applicationComponent().url().setBaseUrl(mockWebServer.url("").toString());

                    if (!needsMockWebServer.setupMethod().isEmpty()) {
                        final Method setupMethod = testClassInstance.getClass().getDeclaredMethod(needsMockWebServer.setupMethod(), MockWebServer.class);
                        setupMethod.invoke(testClassInstance, mockWebServer);
                    }

                    // Try to evaluate the test and anyway shutdown the MockWebServer.
                    try {
                        base.evaluate();
                    } finally {
                        mockWebServer.shutdown();
                    }
                } else {
                    // No need to setup a MockWebServer, just evaluate the test.
                    base.evaluate();
                }
            }
        };
    }

}
