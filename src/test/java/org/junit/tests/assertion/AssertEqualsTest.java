package org.junit.tests.assertion;

import static org.junit.asserts.AssertEquals.assertEquals;
import static org.junit.asserts.AssertEquals.assertNotEquals;
import static org.junit.asserts.AssertTrue.assertTrue;

import org.junit.ComparisonFailure;
import org.junit.Test;
import java.math.BigDecimal;

public class AssertEqualsTest {
    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";
    
    private static class NullToString {
        @Override
        public String toString() {
            return null;
        }
    }
    
    @Test
    public void intsEqualLongs() {
        assertEquals(1, 1L);
    }

    

    @Test
    public void stringsDifferWithUserMessage() {
        try {
            assertEquals("not equal", "one", "two");
        } catch (ComparisonFailure exception) {
            assertEquals("not equal expected:<[one]> but was:<[two]>", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    

    @Test
    public void equals() {
        Object o = new Object();
        assertEquals(o, o);
        assertEquals("abc", "abc");
        assertEquals(true, true);
        assertEquals((byte) 1, (byte) 1);
        assertEquals('a', 'a');
        assertEquals((short) 1, (short) 1);
        assertEquals(1, 1); // int by default, cast is unnecessary
        assertEquals(1l, 1l);
        assertEquals(1.0, 1.0, 0.0);
        assertEquals(1.0d, 1.0d, 0.0d);
    }

    @Test(expected = AssertionError.class)
    public void notEqualsObjectWithNull() {
        assertEquals(new Object(), null);
    }

    @Test(expected = AssertionError.class)
    public void notEqualsNullWithObject() {
        assertEquals(null, new Object());
    }

    @Test
    public void notEqualsObjectWithNullWithMessage() {
        Object o = new Object();
        try {
            assertEquals("message", null, o);
        } catch (AssertionError e) {
            assertEquals("message expected:<null> but was:<" + o.toString() + ">", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void notEqualsNullWithObjectWithMessage() {
        Object o = new Object();
        try {
            assertEquals("message", o, null);
        } catch (AssertionError e) {
            assertEquals("message expected:<" + o.toString() + "> but was:<null>", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test(expected = AssertionError.class)
    public void objectsNotEquals() {
        assertEquals(new Object(), new Object());
    }

    @Test(expected = ComparisonFailure.class)
    public void stringsNotEqual() {
        assertEquals("abc", "def");
    }

    @Test(expected = AssertionError.class)
    public void booleansNotEqual() {
        assertEquals(true, false);
    }

    @Test(expected = AssertionError.class)
    public void bytesNotEqual() {
        assertEquals((byte) 1, (byte) 2);
    }

    @Test(expected = AssertionError.class)
    public void charsNotEqual() {
        assertEquals('a', 'b');
    }

    @Test(expected = AssertionError.class)
    public void shortsNotEqual() {
        assertEquals((short) 1, (short) 2);
    }

    @Test(expected = AssertionError.class)
    public void intsNotEqual() {
        assertEquals(1, 2);
    }

    @Test(expected = AssertionError.class)
    public void longsNotEqual() {
        assertEquals(1l, 2l);
    }

    @Test(expected = AssertionError.class)
    public void floatsNotEqual() {
        assertEquals(1.0, 2.0, 0.9);
    }

    @SuppressWarnings("deprecation")
    @Test(expected = AssertionError.class)
    public void floatsNotEqualWithoutDelta() {
        assertEquals(1.0, 1.1);
    }
    
    @Test(expected = AssertionError.class)
    public void bigDecimalsNotEqual() {
        assertEquals(new BigDecimal("123.4"), new BigDecimal("123.0"));
    }


    @Test(expected = AssertionError.class)
    public void doublesNotEqual() {
        assertEquals(1.0d, 2.0d, 0.9d);
    }

    @Test
    public void naNsAreEqual() {
        assertEquals(Float.NaN, Float.NaN, Float.POSITIVE_INFINITY);
        assertEquals(Double.NaN, Double.NaN, Double.POSITIVE_INFINITY);
    }
    
    @Test
    public void nullMessageDisappearsWithStringAssertEquals() {
        try {
            assertEquals(null, "a", "b");
        } catch (ComparisonFailure e) {
            assertEquals("expected:<[a]> but was:<[b]>", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void nullMessageDisappearsWithAssertEquals() {
        try {
            assertEquals(null, 1, 2);
        } catch (AssertionError e) {
            assertEquals("expected:<1> but was:<2>", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test(expected = AssertionError.class)
    public void arraysDeclaredAsObjectAreComparedAsObjects() {
        Object a1 = new Object[]{"abc"};
        Object a2 = new Object[]{"abc"};
        assertEquals(a1, a2);
    }

    @Test
    public void implicitTypecastEquality() {
        byte b = 1;
        short s = 1;
        int i = 1;
        long l = 1L;
        float f = 1.0f;
        double d = 1.0;

        assertEquals(b, s);
        assertEquals(b, i);
        assertEquals(b, l);
        assertEquals(s, i);
        assertEquals(s, l);
        assertEquals(i, l);
        assertEquals(f, d, 0);
    }

    @Test
    public void errorMessageDistinguishesDifferentValuesWithSameToString() {
        try {
            assertEquals("4", new Integer(4));
        } catch (AssertionError e) {
            assertEquals("expected: java.lang.String<4> but was: java.lang.Integer<4>", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
    @Test
    public void nullAndStringNullPrintCorrectError() {
        try {
            assertEquals(null, "null");
        } catch (AssertionError e) {
            assertEquals("expected: null<null> but was: java.lang.String<null>", e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test(expected = AssertionError.class)
    public void stringNullAndNullWorksToo() {
        assertEquals("null", null);
    }

    

    @Test
    public void nullToString() {
        try {
            assertEquals(new NullToString(), new NullToString());
        } catch (AssertionError e) {
            assertEquals("expected: org.junit.tests.assertion.AssertionTest$NullToString<null> but "
                            + "was: org.junit.tests.assertion.AssertionTest$NullToString<null>",
                    e.getMessage());
            return;
        }

        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test(expected = AssertionError.class)
    public void compareBigDecimalAndInteger() {
        final BigDecimal bigDecimal = new BigDecimal("1.2");
        final Integer integer = Integer.valueOf("1");
        assertEquals(bigDecimal, integer);
    }

    @Test(expected = AssertionError.class)
    public void sameObjectIsNotEqual() {
        Object o = new Object();
        assertNotEquals(o, o);
    }

    @Test
    public void objectsWithDifferentReferencesAreNotEqual() {
        assertNotEquals(new Object(), new Object());
    }

    @Test
    public void assertNotEqualsIncludesCorrectMessage() {
        Integer value1 = new Integer(1);
        Integer value2 = new Integer(1);
        String message = "The values should be different";

        try {
            assertNotEquals(message, value1, value2);
        } catch (AssertionError e) {
            assertEquals(message + ". Actual: " + value1, e.getMessage());
            return;
        }

        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertNotEqualsIncludesTheValueBeingTested() {
        Integer value1 = new Integer(1);
        Integer value2 = new Integer(1);

        try {
            assertNotEquals(value1, value2);
        } catch (AssertionError e) {
            assertTrue(e.getMessage().contains(value1.toString()));
            return;
        }

        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void assertNotEqualsWorksWithPrimitiveTypes() {
        assertNotEquals(1L, 2L);
        assertNotEquals("The values should be different", 1L, 2L);
        assertNotEquals(1.0, 2.0, 0);
        assertNotEquals("The values should be different", 1.0, 2.0, 0);
        assertNotEquals(1.0f, 2.0f, 0f);
        assertNotEquals("The values should be different", 1.0f, 2.0f, 0f);
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsConsidersDeltaCorrectly() {
        assertNotEquals(1.0, 0.9, 0.1);
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsConsidersFloatDeltaCorrectly() {
        assertNotEquals(1.0f, 0.75f, 0.25f);
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsIgnoresDeltaOnNaN() {
        assertNotEquals(Double.NaN, Double.NaN, 1);
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsIgnoresFloatDeltaOnNaN() {
        assertNotEquals(Float.NaN, Float.NaN, 1f);
    }
}
