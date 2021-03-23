package org.junit.tests.assertion;

import static org.junit.asserts.AssertSame.assertSame;
import static org.junit.asserts.AssertSame.assertNotSame;
import static org.junit.asserts.AssertEquals.assertEquals;

import org.junit.Test;

public class AssertSameTest {
    
    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";
    
    @Test
    public void same() {
        Object o1 = new Object();
        assertSame(o1, o1);
    }

    @Test
    public void notSame() {
        Object o1 = new Object();
        Object o2 = new Object();
        assertNotSame(o1, o2);
    }

    @Test(expected = AssertionError.class)
    public void objectsNotSame() {
        assertSame(new Object(), new Object());
    }

    @Test(expected = AssertionError.class)
    public void objectsAreSame() {
        Object o = new Object();
        assertNotSame(o, o);
    }

    @Test
    public void sameWithMessage() {
        try {
            assertSame("not same", "hello", "good-bye");
        } catch (AssertionError exception) {
            assertEquals("not same expected same:<hello> was not:<good-bye>",
                    exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void sameNullMessage() {
        try {
            assertSame("hello", "good-bye");
        } catch (AssertionError exception) {
            assertEquals("expected same:<hello> was not:<good-bye>", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void notSameWithMessage() {
        Object o = new Object();
        try {
            assertNotSame("message", o, o);
        } catch (AssertionError exception) {
            assertEquals("message expected not same", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void notSameNullMessage() {
        Object o = new Object();
        try {
            assertNotSame(o, o);
        } catch (AssertionError exception) {
            assertEquals("expected not same", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
}
