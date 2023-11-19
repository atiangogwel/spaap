package com.spaapp.model.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spaapp.model.business.exception.ServiceLoadException;

public class ServiceLoadExceptionTest {

    @Test
    public void testServiceLoadException() {
        String errorMessage = "Test error message";
        Throwable nestedException = new RuntimeException("Nested exception");

        ServiceLoadException exception = new ServiceLoadException(errorMessage, nestedException);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(nestedException, exception.getCause());
    }
}
