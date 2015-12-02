package hu.supercluster.overpasser.library.query;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OverpassQueryBuilderTest {
    OverpassQueryBuilder queryBuilder;

    @Before
    public void setUp() throws Exception {
        queryBuilder = new OverpassQueryBuilder();
    }

    @Test
    public void testBuild() throws Exception {
        final String s = queryBuilder.build();

        assertEquals("", s);
    }

    @Test
    public void testAppend() throws Exception {
        queryBuilder.append("foo");

        assertEquals("foo", queryBuilder.build());
    }

    @Test
    public void testBoundingBox() throws Exception {
        queryBuilder.boundingBox(1.01d, 2.002d, 3.003d, 4.0004d);

        assertEquals("(1.01,2.002,3.003,4.0004)", queryBuilder.build());
    }

    @Test
    public void testStandaloneParam() throws Exception {
        queryBuilder.standaloneParam("foo");

        assertEquals("[\"foo\"]", queryBuilder.build());
    }

    @Test
    public void testClause() throws Exception {
        queryBuilder.standaloneParam("foo");

        assertEquals("[\"foo\"]", queryBuilder.build());
    }

    @Test
    public void testEquals() throws Exception {
        queryBuilder.equals("foo", "bar");

        assertEquals("[\"foo\"=\"bar\"]", queryBuilder.build());
    }

    @Test
    public void testMultipleValues() throws Exception {
        queryBuilder.multipleValues("foo", new HashSet<>(Arrays.asList("bar", "baz")));

        // It's a set! Order of elements are not guaranteed to be the same
        assertThat(queryBuilder.build(), anyOf(
                equalTo("[\"foo\"~\"bar|baz\"]"),
                equalTo("[\"foo\"~\"baz|bar\"]"))
        );
    }

    @Test
    public void testNotEquals() throws Exception {
        queryBuilder.notEquals("foo", "bar");

        assertEquals("[\"foo\"!=\"bar\"]", queryBuilder.build());
    }

    @Test
    public void testRegexMatches() throws Exception {
        queryBuilder.regexMatches("foo", "bar");

        assertEquals("[\"foo\"~\"bar\"]", queryBuilder.build());
    }

    @Test
    public void testRegexDoesntMatch() throws Exception {
        queryBuilder.regexDoesntMatch("foo", "bar");

        assertEquals("[\"foo\"!~\"bar\"]", queryBuilder.build());
    }
}