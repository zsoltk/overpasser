package hu.supercluster.overpasser.library.query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
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
    public void testPrepareNext() throws Exception {
        filterQuery
                .node()
                .prepareNext()
                .way()
        ;

        verify(builder).append("node");
        verify(builder).append("; ");
        verify(builder).append("way");
        verifyNoMoreInteractions(builder);
    }
}