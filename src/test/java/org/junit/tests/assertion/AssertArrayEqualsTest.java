package org.junit.tests.assertion;

import static org.junit.asserts.AssertArrayEquals.assertArrayEquals;
import static org.junit.asserts.AssertEquals.assertEquals;

import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

public class AssertArrayEqualsTest {
    
    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";
    
    private void assertArrayEqualsFailure(Object[] expecteds, Object[] actuals, String expectedMessage) {
        try {
            assertArrayEquals(expecteds, actuals);
        } catch (ArrayComparisonFailure e) {
            assertEquals(expectedMessage, e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    private void assertArrayEqualsFailure(String message, Object[] expecteds, Object[] actuals, String expectedMessage) {
        try {
            assertArrayEquals(message, expecteds, actuals);
        } catch (ArrayComparisonFailure e) {
            assertEquals(expectedMessage, e.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }
    
    @Test
    public void arraysNotEqual() {
        assertArrayEqualsFailure(
                new Object[]{"right"},
                new Object[]{"wrong"},
                "arrays first differed at element [0]; expected:<[right]> but was:<[wrong]>");
    }

    @Test
    public void arraysNotEqualWithMessage() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[]{"right"},
                new Object[]{"wrong"},
                "not equal: arrays first differed at element [0]; expected:<[right]> but was:<[wrong]>");
    }

    @Test
    public void arraysExpectedNullMessage() {
        try {
            assertArrayEquals("not equal", null, new Object[]{new Object()});
        } catch (AssertionError exception) {
            assertEquals("not equal: expected array was null", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void arraysActualNullMessage() {
        try {
            assertArrayEquals("not equal", new Object[]{new Object()}, null);
        } catch (AssertionError exception) {
            assertEquals("not equal: actual array was null", exception.getMessage());
            return;
        }
        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void arraysDifferentLengthDifferingAtStartMessage() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[]{true},
                new Object[]{false, true},
                "not equal: array lengths differed, expected.length=1 actual.length=2; arrays first differed at element [0]; expected:<true> but was:<false>");
    }

    @Test
    public void arraysDifferentLengthDifferingAtEndMessage() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[]{true},
                new Object[]{true, false},
                "not equal: array lengths differed, expected.length=1 actual.length=2; arrays first differed at element [1]; expected:<end of array> but was:<false>");
    }

    @Test
    public void arraysDifferentLengthDifferingAtEndAndExpectedArrayLongerMessage() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[]{true, false},
                new Object[]{true},
                "not equal: array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1]; expected:<false> but was:<end of array>");
    }

    @Test
    public void arraysElementsDiffer() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[]{"this is a very long string in the middle of an array"},
                new Object[]{"this is another very long string in the middle of an array"},
                "not equal: arrays first differed at element [0]; expected:<this is a[] very long string in...> but was:<this is a[nother] very long string in...>");
    }

    @Test
    public void arraysDifferAtElement0nullMessage() {
        assertArrayEqualsFailure(
                new Object[]{true},
                new Object[]{false},
                "arrays first differed at element [0]; expected:<true> but was:<false>"
        );
    }

    @Test
    public void arraysDifferAtElement1nullMessage() {
        assertArrayEqualsFailure(
                new Object[]{true, true},
                new Object[]{true, false},
                "arrays first differed at element [1]; expected:<true> but was:<false>"
        );
    }

    @Test
    public void arraysDifferAtElement0withMessage() {
        assertArrayEqualsFailure(
                "message",
                new Object[]{true},
                new Object[]{false},
                "message: arrays first differed at element [0]; expected:<true> but was:<false>"
        );
    }

    @Test
    public void arraysDifferAtElement1withMessage() {
        assertArrayEqualsFailure(
                "message",
                new Object[]{true, true},
                new Object[]{true, false},
                "message: arrays first differed at element [1]; expected:<true> but was:<false>"
        );
    }

    @Test
    public void multiDimensionalArraysAreEqual() {
        assertArrayEquals((new Object[][]{{true, true}, {false, false}}), (new Object[][]{{true, true}, {false, false}}));
    }

    @Test
    public void multiDimensionalIntArraysAreEqual() {
        int[][] int1 = {{1, 2, 3}, {4, 5, 6}};
        int[][] int2 = {{1, 2, 3}, {4, 5, 6}};
        assertArrayEquals(int1, int2);
    }

    @Test
    public void oneDimensionalPrimitiveArraysAreEqual() {
        assertArrayEquals(new boolean[]{true}, new boolean[]{true});
        assertArrayEquals(new byte[]{1}, new byte[]{1});
        assertArrayEquals(new char[]{1}, new char[]{1});
        assertArrayEquals(new short[]{1}, new short[]{1});
        assertArrayEquals(new int[]{1}, new int[]{1});
        assertArrayEquals(new long[]{1}, new long[]{1});
        assertArrayEquals(new double[]{1.0}, new double[]{1.0}, 1.0);
        assertArrayEquals(new float[]{1.0f}, new float[]{1.0f}, 1.0f);
    }

    @Test(expected = AssertionError.class)
    public void oneDimensionalDoubleArraysAreNotEqual() {
        assertArrayEquals(new double[]{1.0}, new double[]{2.5}, 1.0);
    }

    @Test(expected = AssertionError.class)
    public void oneDimensionalFloatArraysAreNotEqual() {
        assertArrayEquals(new float[]{1.0f}, new float[]{2.5f}, 1.0f);
    }

    @Test(expected = AssertionError.class)
    public void oneDimensionalBooleanArraysAreNotEqual() {
        assertArrayEquals(new boolean[]{true}, new boolean[]{false});
    }
    
    @Test
    public void multiDimensionalArraysDeclaredAsOneDimensionalAreEqual() {
        assertArrayEquals((new Object[]{new Object[]{true, true}, new Object[]{false, false}}), (new Object[]{new Object[]{true, true}, new Object[]{false, false}}));
    }

    @Test
    public void multiDimensionalArraysAreNotEqual() {
        assertArrayEqualsFailure(
                "message",
                new Object[][]{{true, true}, {false, false}},
                new Object[][]{{true, true}, {true, false}},
                "message: arrays first differed at element [1][0]; expected:<false> but was:<true>");
    }

    @Test
    public void multiDimensionalArraysAreNotEqualNoMessage() {
        assertArrayEqualsFailure(
                new Object[][]{{true, true}, {false, false}},
                new Object[][]{{true, true}, {true, false}},
                "arrays first differed at element [1][0]; expected:<false> but was:<true>");
    }

    @Test
    public void twoDimensionalArraysDifferentOuterLengthNotEqual() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{true}, {}},
                new Object[][]{{}},
                "not equal: array lengths differed, expected.length=1 actual.length=0; arrays first differed at element [0][0]; expected:<true> but was:<end of array>");
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{}, {true}},
                new Object[][]{{}},
                "not equal: array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1]; expected:<java.lang.Object[1]> but was:<end of array>");
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{}},
                new Object[][]{{true}, {}},
                "not equal: array lengths differed, expected.length=0 actual.length=1; arrays first differed at element [0][0]; expected:<end of array> but was:<true>");
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{}},
                new Object[][]{{}, {true}},
                "not equal: array lengths differed, expected.length=1 actual.length=2; arrays first differed at element [1]; expected:<end of array> but was:<java.lang.Object[1]>");
    }

    @Test
    public void primitiveArraysConvertedToStringCorrectly() {
        assertArrayEqualsFailure(
                "not equal",
                new boolean[][]{{}, {true}},
                new boolean[][]{{}},
                "not equal: array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1]; expected:<boolean[1]> but was:<end of array>");
        assertArrayEqualsFailure(
                "not equal",
                new int[][]{{}, {23}},
                new int[][]{{}},
                "not equal: array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1]; expected:<int[1]> but was:<end of array>");
    }

    @Test
    public void twoDimensionalArraysConvertedToStringCorrectly() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[][][]{{}, {{true}}},
                new Object[][][]{{}},
                "not equal: array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1]; expected:<java.lang.Object[][1]> but was:<end of array>");
    }

    @Test
    public void twoDimensionalArraysDifferentInnerLengthNotEqual() {
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{true}, {}},
                new Object[][]{{}, {}},
                "not equal: array lengths differed, expected.length=1 actual.length=0; arrays first differed at element [0][0]; expected:<true> but was:<end of array>");
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{}, {true}},
                new Object[][]{{}, {}},
                "not equal: array lengths differed, expected.length=1 actual.length=0; arrays first differed at element [1][0]; expected:<true> but was:<end of array>");
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{}, {}},
                new Object[][]{{true}, {}},
                "not equal: array lengths differed, expected.length=0 actual.length=1; arrays first differed at element [0][0]; expected:<end of array> but was:<true>");
        assertArrayEqualsFailure(
                "not equal",
                new Object[][]{{}, {}},
                new Object[][]{{}, {true}},
                "not equal: array lengths differed, expected.length=0 actual.length=1; arrays first differed at element [1][0]; expected:<end of array> but was:<true>");
    }

    

    @Test
    public void multiDimensionalArraysDifferentLengthMessage() {
        try {
            assertArrayEquals("message", new Object[][]{{true, true}, {false, false}}, new Object[][]{{true, true}, {false}});
        } catch (AssertionError exception) {
            assertEquals("message: array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1][1]; expected:<false> but was:<end of array>", exception.getMessage());
            return;
        }

        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void multiDimensionalArraysDifferentLengthNoMessage() {
        try {
            assertArrayEquals(new Object[][]{{true, true}, {false, false}}, new Object[][]{{true, true}, {false}});
        } catch (AssertionError exception) {
            assertEquals("array lengths differed, expected.length=2 actual.length=1; arrays first differed at element [1][1]; expected:<false> but was:<end of array>", exception.getMessage());
            return;
        }

        throw new AssertionError(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void arraysWithNullElementEqual() {
        Object[] objects1 = new Object[]{null};
        Object[] objects2 = new Object[]{null};
        assertArrayEquals(objects1, objects2);
    }
    
    @Test
    public void arraysEqual() {
        Object element = new Object();
        Object[] objects1 = new Object[]{element};
        Object[] objects2 = new Object[]{element};
        assertArrayEquals(objects1, objects2);
    }

    @Test
    public void arraysEqualWithMessage() {
        Object element = new Object();
        Object[] objects1 = new Object[]{element};
        Object[] objects2 = new Object[]{element};
        assertArrayEquals("equal", objects1, objects2);
    }
    
    @Test
    public void floatsNotDoublesInArrays() {
        float delta = 4.444f;
        float[] f1 = new float[]{1.111f};
        float[] f2 = new float[]{5.555f};
        assertArrayEquals(f1, f2, delta);
    }
}
