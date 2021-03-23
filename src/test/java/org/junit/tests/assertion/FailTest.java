package org.junit.tests.assertion;

import static org.junit.asserts.Fail.fail;
import static org.junit.asserts.AssertEquals.*;

import org.junit.Test;



public class FailTest {
    
    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";
    
    @Test(expected = AssertionError.class)
    public void fails() {
        fail();
    }

    @Test
    public void failWithNoMessageToString() {
        try {
            fail();
        } catch (AssertionError exception) {
            assertEquals("java.lang.AssertionError", exception.toString());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void failWithMessageToString() {
        try {
            fail("woops!");
        } catch (AssertionError exception) {
            assertEquals("java.lang.AssertionError: woops!", exception.toString());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
}
