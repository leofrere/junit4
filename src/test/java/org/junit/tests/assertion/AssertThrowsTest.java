package org.junit.tests.assertion;

import static org.junit.asserts.AssertEquals.assertEquals;
import static org.junit.asserts.AssertThrows.assertThrows;
import static org.junit.asserts.AssertSame.assertSame;


import java.io.IOException;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class AssertThrowsTest {

    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";
    
    private static class NestedException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    private static ThrowingRunnable nonThrowingRunnable() {
        return new ThrowingRunnable() {
            public void run() throws Throwable {
            }
        };
    }

    private static ThrowingRunnable throwingRunnable(final Throwable t) {
        return new ThrowingRunnable() {
            public void run() throws Throwable {
                throw t;
            }
        };
    }
    
    @Test(expected = AssertionError.class)
    public void assertThrowsRequiresAnExceptionToBeThrown() {
        assertThrows(Throwable.class, nonThrowingRunnable());
    }

    @Test
    public void assertThrowsIncludesAnInformativeDefaultMessage() {
        try {
            assertThrows(Throwable.class, nonThrowingRunnable());
        } catch (AssertionError ex) {
            assertEquals("expected java.lang.Throwable to be thrown, but nothing was thrown", ex.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsIncludesTheSpecifiedMessage() {
        try {
            assertThrows("Foobar", Throwable.class, nonThrowingRunnable());
        } catch (AssertionError ex) {
            assertEquals(
                    "Foobar: expected java.lang.Throwable to be thrown, but nothing was thrown",
                    ex.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsReturnsTheSameObjectThrown() {
        NullPointerException npe = new NullPointerException();

        Throwable throwable = assertThrows(Throwable.class, throwingRunnable(npe));

        assertSame(npe, throwable);
    }

    @Test(expected = AssertionError.class)
    public void assertThrowsDetectsTypeMismatchesViaExplicitTypeHint() {
        NullPointerException npe = new NullPointerException();

        assertThrows(IOException.class, throwingRunnable(npe));
    }

    @Test
    public void assertThrowsWrapsAndPropagatesUnexpectedExceptions() {
        NullPointerException npe = new NullPointerException("inner-message");

        try {
            assertThrows(IOException.class, throwingRunnable(npe));
        } catch (AssertionError ex) {
            assertSame(npe, ex.getCause());
            assertEquals("inner-message", ex.getCause().getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsSuppliesACoherentErrorMessageUponTypeMismatch() {
        NullPointerException npe = new NullPointerException();

        try {
            assertThrows(IOException.class, throwingRunnable(npe));
        } catch (AssertionError error) {
            assertEquals("unexpected exception type thrown; expected:<java.io.IOException> but was:<java.lang.NullPointerException>",
                    error.getMessage());
            assertSame(npe, error.getCause());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsSuppliesTheSpecifiedMessageUponTypeMismatch() {
        NullPointerException npe = new NullPointerException();

        try {
            assertThrows("Foobar", IOException.class, throwingRunnable(npe));
        } catch (AssertionError error) {
            assertEquals("Foobar: unexpected exception type thrown; expected:<java.io.IOException> but was:<java.lang.NullPointerException>",
                    error.getMessage());
            assertSame(npe, error.getCause());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsUsesCanonicalNameUponTypeMismatch() {
        NullPointerException npe = new NullPointerException();

        try {
            assertThrows(NestedException.class, throwingRunnable(npe));
        } catch (AssertionError error) {
            assertEquals(
                    "unexpected exception type thrown; expected:<org.junit.tests.assertion.AssertThrowsTest.NestedException>"
                    + " but was:<java.lang.NullPointerException>",
                    error.getMessage());
            assertSame(npe, error.getCause());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsUsesNameUponTypeMismatchWithAnonymousClass() {
        NullPointerException npe = new NullPointerException() {
        };

        try {
            assertThrows(IOException.class, throwingRunnable(npe));
        } catch (AssertionError error) {
            assertEquals(
                    "unexpected exception type thrown; expected:<java.io.IOException>"
                    + " but was:<org.junit.tests.assertion.AssertThrowsTest$3>",
                    error.getMessage());
            assertSame(npe, error.getCause());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertThrowsUsesCanonicalNameWhenRequiredExceptionNotThrown() {
        try {
            assertThrows(NestedException.class, nonThrowingRunnable());
        } catch (AssertionError error) {
            assertEquals(
                    "expected org.junit.tests.assertion.AssertThrowsTest.NestedException to be thrown,"
                    + " but nothing was thrown", error.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
}
