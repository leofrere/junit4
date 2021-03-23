package org.junit.tests.assertion;

import static org.junit.asserts.AssertNull.assertNull;
import static org.junit.asserts.AssertEquals.assertEquals;
import static org.junit.asserts.Fail.fail;

import org.junit.Test;

public class AssertNullTest {
    
    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";
    
    @SuppressWarnings("unused")
    @Test
    public void nullNullmessage() {
        try {
            assertNull("junit");
        } catch (AssertionError e) {
            assertEquals("expected null, but was:<junit>", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @SuppressWarnings("unused")
    @Test
    public void nullWithMessage() {
        try {
            assertNull("message", "hello");
        } catch (AssertionError exception) {
            assertEquals("message expected null, but was:<hello>", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
    @Test
    public void nullMessage() {
        try {
            fail(null);
        } catch (AssertionError exception) {
            // we used to expect getMessage() to return ""; see failWithNoMessageToString()
            assertNull(exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
}
