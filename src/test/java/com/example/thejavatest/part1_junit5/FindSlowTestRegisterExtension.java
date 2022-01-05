package com.example.thejavatest.part1_junit5;

import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class FindSlowTestRegisterExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private Long THRESHOLD;

    public FindSlowTestRegisterExtension(Long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        // Reflection
        Method requiredTestMethod = context.getRequiredTestMethod();
        String testMethodName = requiredTestMethod.getName();

        Store store = getStore(context);
        Long start_time = store.remove("START_TIME", Long.class);
        Long duration = System.currentTimeMillis() - start_time;

        SlowTest slowTestAnnotation = requiredTestMethod.getAnnotation(SlowTest.class);
        if(duration > THRESHOLD && slowTestAnnotation == null) {
            System.out.printf("Please consider mark method [%s] with @SlowTest.\n", testMethodName);
        }
    }

    private Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        Store store = context.getStore(Namespace.create(testClassName, testMethodName));
        return store;
    }

}
