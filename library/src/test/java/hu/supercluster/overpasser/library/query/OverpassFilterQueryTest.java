package hu.supercluster.overpasser.library.query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class OverpassFilterQueryTest {
    static final String START_TOKEN = "; (";
    static final String END_TOKEN = ";<;)";

    @Mock OverpassQueryBuilder builder;
    @Mock OverpassQuery query;
    OverpassFilterQuery filterQuery;

    public OverpassFilterQueryTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        filterQuery = new OverpassFilterQuery(query, builder);

        verify(builder).append(START_TOKEN);
    }

    @Test
    public void testBuild() throws Exception {
        filterQuery.build();

        verify(builder).append(END_TOKEN);
        verify(builder).build();
        verifyNoMoreInteractions(builder);
    }

    @Test
    public void testNode() throws Exception {
        filterQuery.node();

        verify(builder).append("node");
        verifyNoMoreInteractions(builder);
    }

    @Test
    public void testWay() throws Exception {
        filterQuery.way();

        verify(builder).append("way");
        verifyNoMoreInteractions(builder);
    }

    @Test
    public void testRel() throws Exception {
        filterQuery.rel();

        verify(builder).append("rel");
        verifyNoMoreInteractions(builder);
    }

    @Test
    public void testArea() throws Exception {
        filterQuery.area();

        verify(builder).append("area");
        verifyNoMoreInteractions(builder);
    }

    @Test
    public void testPrepareNext() throws Exception {
        filterQuery
                .node()
                .prepareNext()
                .way()
                .prepareNext()
                .rel()
        ;

        verify(builder).append("node");
        verify(builder).append("way");
        verify(builder).append("rel");
        verify(builder, times(2)).append("; ");
        verifyNoMoreInteractions(builder);
    }


    @Test
    public void testAmenity() throws Exception {
        filterQuery.amenity("parking");

        verify(builder).equals("amenity", "parking");
    }

    @Test
    public void testAmenities() throws Exception {
        final HashSet<String> amenities = new HashSet<>(Arrays.asList("shop", "market"));
        filterQuery.amenities(amenities);

        verify(builder).multipleValues("amenity", amenities);
    }

    @Test
    public void testTag1() throws Exception {
        filterQuery.tag("foo");

        verify(builder).standaloneParam("foo");
    }


    @Test
    public void testTag2() throws Exception {
        filterQuery.tag("foo", "bar");

        verify(builder).equals("foo", "bar");
    }

    @Test
    public void testTagNot() throws Exception {
        filterQuery.tagNot("foo", "bar");

        verify(builder).notEquals("foo", "bar");
    }

    @Test
    public void testTagMultiple() throws Exception {
        final HashSet<String> values = new HashSet<>(Arrays.asList("bar", "baz"));
        filterQuery.tagMultiple("foo", values);

        verify(builder).multipleValues("foo", values);
    }

    @Test
    public void testTagRegex() throws Exception {
        filterQuery.tagRegex("foo", "bar");

        verify(builder).regexMatches("foo", "bar");
    }

    @Test
    public void testTagRegexNot() throws Exception {
        filterQuery.tagRegexNot("foo", "bar");

        verify(builder).regexDoesntMatch("foo", "bar");
    }

    @Test
    public void testBoundingBox() throws Exception {
        double lat1 = 1.0d;
        double lon1 = 2.0d;
        double lat2 = 3.0d;
        double lon2 = 4.0d;
        filterQuery.boundingBox(lat1, lon1, lat2, lon2);

        verify(builder).boundingBox(lat1, lon1, lat2, lon2);
    }
}