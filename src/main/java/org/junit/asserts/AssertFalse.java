package org.junit.asserts;


public class AssertFalse {
    
    protected AssertFalse() {
    }
    
    /**
     * Asserts that a condition is false. If it isn't it throws an
     * {@link AssertionError} with the given message.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param condition condition to be checked
     */
    public static void assertFalse(String message, boolean condition) {
        AssertTrue.assertTrue(message, !condition);
    }
    
    /**
     * Asserts that a condition is false. If it isn't it throws an
     * {@link AssertionError} without a message.
     *
     * @param condition condition to be checked
     */
    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }
}
