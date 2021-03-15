package org.junit.asserts;

public class Format {
    
    public static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null && !"".equals(message)) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        if (equalsRegardingNull(expectedString, actualString)) {
            return formatted + "expected: "
                    + formatClassAndValue(expected, expectedString)
                    + " but was: " + formatClassAndValue(actual, actualString);
        } else {
            return formatted + "expected:<" + expectedString + "> but was:<"
                    + actualString + ">";
        }
    }

    public static String formatClass(Class<?> value) {
        String className = value.getCanonicalName();
        return className == null ? value.getName() : className;
    }

    public static String formatClassAndValue(Object value, String valueString) {
        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }
    public static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }

        return isEquals(expected, actual);
    }
    
    public static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }
    
}
