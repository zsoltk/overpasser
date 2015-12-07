package hu.supercluster.overpasser.library.query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class OverpassFilterQueryTest {
    static final String START_TOKEN = "; (";
    static final String END_TOKEN = ";<;)";
    @Mock OverpassQuery query;
    OverpassFilterQuery filterQuery;

    public OverpassFilterQueryTest() {
        MockitoAnnotations.initMocks(this);
    }

    private String build(String s) {
        return START_TOKEN + s + END_TOKEN;
    }

    @Before
    public void setUp() throws Exception {
        filterQuery = new OverpassFilterQuery(query);
    }

    @Test
    public void testBuild() throws Exception {
        assertEquals(build(""), filterQuery.build());
    }

    @Test
    public void testNode() throws Exception {
        filterQuery.node();

        assertEquals(build("node"), filterQuery.build());
    }

    @Test
    public void testWay() throws Exception {
        filterQuery.way();

        assertEquals(build("way"), filterQuery.build());
    }

    @Test
    public void testPrepareNext() throws Exception {
        filterQuery
                .node()
                .prepareNext()
                .way()
        ;

        assertEquals(build("node; way"), filterQuery.build());
    }
}