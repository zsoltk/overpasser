package hu.supercluster.overpasser.library.query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.supercluster.overpasser.library.output.OutputModificator;
import hu.supercluster.overpasser.library.output.OutputOrder;
import hu.supercluster.overpasser.library.output.OutputVerbosity;

import static hu.supercluster.overpasser.library.output.OutputFormat.*;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class OverpassQueryTest {
    @Mock OverpassQueryBuilder builder;
    private OverpassQuery query;

    public OverpassQueryTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        query = new OverpassQuery(builder);
    }

    @Test
    public void testBuild() throws Exception {
        query.build();

        verify(builder).append(";");
    }

    @Test
    public void testFormat() throws Exception {
        query.format(JSON);

        verify(builder).clause("out", "json");
    }

    @Test
    public void testTimeout() throws Exception {
        query.timeout(30);

        verify(builder).clause("timeout", "30");
    }

    @Test
    public void testFilterQuery() throws Exception {
        OverpassFilterQuery filterQuery = query.filterQuery();

        assertThat(filterQuery, isA(OverpassFilterQuery.class));
    }

    @Test
    public void testOutputWithDefaultArgs() throws Exception {
        query.output(100);

        verify(builder).append("; out body center qt 100");
    }

    @Test
    public void testOutputWithCustomArgs() throws Exception {
        query.output(OutputVerbosity.META, OutputModificator.GEOM, OutputOrder.ASC, 100);

        verify(builder).append("; out meta geom asc 100");
    }

    @Test
    public void testExample1() throws Exception {
        String result = new OverpassQuery()
                .format(JSON)
                .timeout(30)
                .filterQuery()
                    .node()
                    .amenity("parking")
                    .notEquals("access", "private")
                    .boundingBox(
                            47.48047027491862, 19.039797484874725,
                            47.51331674014172, 19.07404761761427
                    )
                .end()
                .output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 100)
                .build()
        ;

        String expected = "[\"out\":\"json\"][\"timeout\":\"30\"]; (node[\"amenity\"=\"parking\"][\"access\"!=\"private\"](47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427);<;); out body center qt 100;";

        assertEquals(expected, result);
    }

    @Test
    public void testExample2() throws Exception {
        String result = new OverpassQuery()
                .format(JSON)
                .timeout(30)
                .filterQuery()
                    .way()
                    .amenity("parking")
                    .notEquals("access", "private")
                    .boundingBox(
                            47.48047027491862, 19.039797484874725,
                            47.51331674014172, 19.07404761761427
                    )
                .end()
                .output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 100)
                .build()
        ;

        String expected = "[\"out\":\"json\"][\"timeout\":\"30\"]; (way[\"amenity\"=\"parking\"][\"access\"!=\"private\"](47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427);<;); out body center qt 100;";

        assertEquals(expected, result);
    }
}