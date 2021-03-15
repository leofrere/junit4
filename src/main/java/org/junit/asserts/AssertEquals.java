package org.junit.asserts;

import org.junit.ComparisonFailure;

public class AssertEquals {
    
    protected AssertEquals() {
    }
    
    /**
     * Asserts that two objects are equal. If they are not, an
     * {@link AssertionError} is thrown with the given message. If
     * <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param expected expected value
     * @param actual actual value
     */
    public static void assertEquals(String message, Object expected,
            Object actual) {
        if (equalsRegardingNull(expected, actual)) {
            return;
        }
        if (expected instanceof String && actual instanceof String) {
            String cleanMessage = message == null ? "" : message;
            throw new ComparisonFailure(cleanMessage, (String) expected,
                    (String) actual);
        } else {
            Fail.failNotEquals(message, expected, actual);
        }
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }

        return isEquals(expected, actual);
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }
    
    /**
     * Asserts that two objects are equal. If they are not, an
     * {@link AssertionError} without a message is thrown. If
     * <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     */
    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two objects are <b>not</b> equals. If they are, an
     * {@link AssertionError} is thrown with the given message. If
     * <code>unexpected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param unexpected unexpected value to check
     * @param actual the value to check against <code>unexpected</code>
     */
    public static void assertNotEquals(String message, Object unexpected,
            Object actual) {
        if (equalsRegardingNull(unexpected, actual)) {
            Fail.failEquals(message, actual);
        }
    }
    
    public static void assertNotEquals(Object unexpected, Object actual) {
        assertNotEquals(null, unexpected, actual);
    }
    
    /**
     * Asserts that two longs are <b>not</b> equals. If they are, an
     * {@link AssertionError} is thrown with the given message.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param unexpected unexpected value to check
     * @param actual the value to check against <code>unexpected</code>
     */
    public static void assertNotEquals(String message, long unexpected, long actual) {
        if (unexpected == actual) {
            Fail.failEquals(message, Long.valueOf(actual));
        }
    }


    /**
     * Asserts that two longs are <b>not</b> equals. If they are, an
     * {@link AssertionError} without a message is thrown.
     *
     * @param unexpected unexpected value to check
     * @param actual the value to check against <code>unexpected</code>
     */
    public static void assertNotEquals(long unexpected, long actual) {
        assertNotEquals(null, unexpected, actual);
    }

    /**
     * Asserts that two doubles are <b>not</b> equal to within a positive delta.
     * If they are, an {@link AssertionError} is thrown with the given
     * message. If the unexpected value is infinity then the delta value is
     * ignored. NaNs are considered equal:
     * <code>assertNotEquals(Double.NaN, Double.NaN, *)</code> fails
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param unexpected unexpected value
     * @param actual the value to check against <code>unexpected</code>
     * @param delta the maximum delta between <code>unexpected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertNotEquals(String message, double unexpected,
            double actual, double delta) {
        if (!doubleIsDifferent(unexpected, actual, delta)) {
            Fail.failEquals(message, Double.valueOf(actual));
        }
    }

    /**
     * Asserts that two doubles are <b>not</b> equal to within a positive delta.
     * If they are, an {@link AssertionError} is thrown. If the unexpected
     * value is infinity then the delta value is ignored.NaNs are considered
     * equal: <code>assertNotEquals(Double.NaN, Double.NaN, *)</code> fails
     *
     * @param unexpected unexpected value
     * @param actual the value to check against <code>unexpected</code>
     * @param delta the maximum delta between <code>unexpected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertNotEquals(double unexpected, double actual, double delta) {
        assertNotEquals(null, unexpected, actual, delta);
    }

    /**
     * Asserts that two floats are <b>not</b> equal to within a positive delta.
     * If they are, an {@link AssertionError} is thrown. If the unexpected
     * value is infinity then the delta value is ignored.NaNs are considered
     * equal: <code>assertNotEquals(Float.NaN, Float.NaN, *)</code> fails
     *
     * @param unexpected unexpected value
     * @param actual the value to check against <code>unexpected</code>
     * @param delta the maximum delta between <code>unexpected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertNotEquals(float unexpected, float actual, float delta) {
        assertNotEquals(null, unexpected, actual, delta);
    }

    /**
     * Asserts that two doubles are equal to within a positive delta.
     * If they are not, an {@link AssertionError} is thrown with the given
     * message. If the expected value is infinity then the delta value is
     * ignored. NaNs are considered equal:
     * <code>assertEquals(Double.NaN, Double.NaN, *)</code> passes
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     * @param delta the maximum delta between <code>expected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertEquals(String message, double expected,
            double actual, double delta) {
        if (doubleIsDifferent(expected, actual, delta)) {
            Fail.failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
        }
    }

    /**
     * Asserts that two floats are equal to within a positive delta.
     * If they are not, an {@link AssertionError} is thrown with the given
     * message. If the expected value is infinity then the delta value is
     * ignored. NaNs are considered equal:
     * <code>assertEquals(Float.NaN, Float.NaN, *)</code> passes
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     * @param delta the maximum delta between <code>expected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertEquals(String message, float expected,
            float actual, float delta) {
        if (floatIsDifferent(expected, actual, delta)) {
            Fail.failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
        }
    }

    /**
     * Asserts that two floats are <b>not</b> equal to within a positive delta.
     * If they are, an {@link AssertionError} is thrown with the given
     * message. If the unexpected value is infinity then the delta value is
     * ignored. NaNs are considered equal:
     * <code>assertNotEquals(Float.NaN, Float.NaN, *)</code> fails
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param unexpected unexpected value
     * @param actual the value to check against <code>unexpected</code>
     * @param delta the maximum delta between <code>unexpected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertNotEquals(String message, float unexpected,
            float actual, float delta) {
        if (!floatIsDifferent(unexpected, actual, delta)) {
            Fail.failEquals(message, Float.valueOf(actual));
        }
    }
    
    /**
     * Asserts that two longs are equal. If they are not, an
     * {@link AssertionError} is thrown.
     *
     * @param expected expected long value.
     * @param actual actual long value
     */
    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two longs are equal. If they are not, an
     * {@link AssertionError} is thrown with the given message.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param expected long expected value.
     * @param actual long actual value
     */
    public static void assertEquals(String message, long expected, long actual) {
        if (expected != actual) {
            Fail.failNotEquals(message, Long.valueOf(expected), Long.valueOf(actual));
        }
    }

    /**
     * Asserts that two doubles are equal to within a positive delta.
     * If they are not, an {@link AssertionError} is thrown. If the expected
     * value is infinity then the delta value is ignored.NaNs are considered
     * equal: <code>assertEquals(Double.NaN, Double.NaN, *)</code> passes
     *
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     * @param delta the maximum delta between <code>expected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two floats are equal to within a positive delta.
     * If they are not, an {@link AssertionError} is thrown. If the expected
     * value is infinity then the delta value is ignored. NaNs are considered
     * equal: <code>assertEquals(Float.NaN, Float.NaN, *)</code> passes
     *
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     * @param delta the maximum delta between <code>expected</code> and
     * <code>actual</code> for which both numbers are still
     * considered equal.
     */
    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that an object isn't null. If it is an {@link AssertionError} is
     * thrown with the given message.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     * okay)
     * @param object Object to check or <code>null</code>
     */
    public static void assertNotNull(String message, Object object) {
        AssertTrue.assertTrue(message, object != null);
    }
    
    private static boolean doubleIsDifferent(double d1, double d2, double delta) {
        if (Double.compare(d1, d2) == 0) {
            return false;
        }
        if ((Math.abs(d1 - d2) <= delta)) {
            return false;
        }

        return true;
    }
    
    private static boolean floatIsDifferent(float f1, float f2, float delta) {
        if (Float.compare(f1, f2) == 0) {
            return false;
        }
        if ((Math.abs(f1 - f2) <= delta)) {
            return false;
        }

        return true;
    }
    
}
