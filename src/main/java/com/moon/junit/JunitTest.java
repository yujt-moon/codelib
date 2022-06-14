package com.moon.junit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * https://jenkov.com/tutorials/java-unit-testing/matchers.html
 * @author yujiangtaoa
 * @date 2022/4/28 上午10:59
 */
public class JunitTest {

    @Test
    public void testWithMatchers() {
        assertThat("this string", is("this string"));
        assertThat(123, is(123));
    }

    @Test
    public void testWithChainingMatchers() {
        assertThat(123, not(is(345)));
    }

    @Test
    public void testCustomMatcher() {
        assertThat("constant string", JunitTest.matches("constant string"));
    }

    public static Matcher matches(final Object expected) {
        return new BaseMatcher() {

            protected Object theExpected = expected;

            @Override
            public boolean matches(Object o) {
                return theExpected.equals(o);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(theExpected.toString());
            }
        };
    }
}
