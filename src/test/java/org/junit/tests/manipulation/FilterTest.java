package org.junit.tests.manipulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

public class FilterTest {
    public static class NamedFilter extends Filter {
        private final String fName;

        public NamedFilter(String name) {
            fName = name;
        }

        @Override
        public boolean shouldRun(Description description) {
            return false;
        }

        @Override
        public String describe() {
            return fName;
        }
    }

    @Test
    public void intersectionText() {
        NamedFilter filterA = new NamedFilter("a");
        NamedFilter filterB = new NamedFilter("b");
        assertEquals("a and b", filterA.intersect(filterB).describe());
        assertEquals("b and a", filterB.intersect(filterA).describe());
    }

    @Test
    public void intersectSelf() {
        NamedFilter filterA = new NamedFilter("a");
        assertSame(filterA, filterA.intersect(filterA));
    }

    @Test
    public void intersectAll() {
        NamedFilter filterA = new NamedFilter("a");
        assertSame(filterA, filterA.intersect(Filter.ALL));
        assertSame(filterA, Filter.ALL.intersect(filterA));
        assertSame(Filter.ALL, Filter.ALL.intersect(Filter.ALL));
    }
}
