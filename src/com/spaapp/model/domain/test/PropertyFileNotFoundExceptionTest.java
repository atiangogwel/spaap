package com.spaapp.model.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spaapp.model.business.exception.PropertyFileNotFoundException;

public class PropertyFileNotFoundExceptionTest {

    @Test
    public void testPropertyFileNotFoundException() {
        String errorMessage = "Test error message";
        Throwable nestedException = new RuntimeException("Nested exception");

        PropertyFileNotFoundException exception = new PropertyFileNotFoundException(errorMessage, nestedException);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(nestedException, exception.getCause());
    }
}
