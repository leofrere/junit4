package org.junit.tests.assertion;

import static org.junit.asserts.AssertEquals.assertEquals;

import org.junit.ComparisonFailure;
import org.junit.Test;
import java.math.BigDecimal;

public class AssertEqualsTest {
    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";

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
}
