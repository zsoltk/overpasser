package hu.supercluster.overpasser.library.query;

import org.junit.Before;
import org.junit.Test;

import hu.supercluster.overpasser.library.output.OutputModificator;
import hu.supercluster.overpasser.library.output.OutputOrder;
import hu.supercluster.overpasser.library.output.OutputVerbosity;

import static hu.supercluster.overpasser.library.output.OutputFormat.*;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;

public class OverpassQueryTest {

    private OverpassQuery query;

    @Before
    public void setUp() throws Exception {
        query = new OverpassQuery();
    }

    @Test
    public void testBuild() throws Exception {
        final String s = query.build();

        assertEquals(";", s);
    }

    @Test
    public void testFormat() throws Exception {
        query.format(JSON);

        assertEquals("[\"out\":\"json\"];", query.build());
    }

    @Test
    public void testTimeout() throws Exception {
        query.timeout(30);

        assertEquals("[\"timeout\":\"30\"];", query.build());
    }

    @Test
    public void testFilterQuery() throws Exception {
        OverpassFilterQuery filterQuery = query.filterQuery();

        assertThat(filterQuery, isA(OverpassFilterQuery.class));
    }

    @Test
    public void testOutputWithDefaultArgs() throws Exception {
        query.output(100);

        assertEquals("; out body center qt 100;", query.build());
    }

    @Test
    public void testOutputWithCustomArgs() throws Exception {
        query.output(OutputVerbosity.META, OutputModificator.GEOM, OutputOrder.ASC, 100);

        assertEquals("; out meta geom asc 100;", query.build());
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