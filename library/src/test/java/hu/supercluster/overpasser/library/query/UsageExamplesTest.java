package hu.supercluster.overpasser.library.query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.supercluster.overpasser.library.output.OutputModificator;
import hu.supercluster.overpasser.library.output.OutputOrder;
import hu.supercluster.overpasser.library.output.OutputVerbosity;

import static hu.supercluster.overpasser.library.output.OutputFormat.JSON;
import static org.junit.Assert.assertEquals;

public class UsageExamplesTest {
    @Mock OverpassQueryBuilder builder;
    private OverpassQuery query;

    public UsageExamplesTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        query = new OverpassQuery(builder);
    }

    @Test
    public void testSimpleFilterQuery() throws Exception {
        String result = new OverpassQuery()
                .format(JSON)
                .timeout(30)
                .filterQuery()
                    .node()
                    .amenity("parking")
                    .tagNot("access", "private")
                    .boundingBox(
                            47.48047027491862, 19.039797484874725,
                            47.51331674014172, 19.07404761761427
                    )
                .end()
                .output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 100)
                .build()
        ;

        String expected = "[out:\"json\"][timeout:\"30\"]; (node[\"amenity\"=\"parking\"][\"access\"!=\"private\"](47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427);<;); out body center qt 100;";

        assertEquals(expected, result);
    }

    @Test
    public void testMultipleFilterQueries() throws Exception {
        String result = new OverpassQuery()
                .format(JSON)
                .timeout(30)
                .filterQuery()
                    .node()
                        .amenity("parking")
                        .tagNot("access", "private")
                        .boundingBox(
                                47.48047027491862, 19.039797484874725,
                                47.51331674014172, 19.07404761761427
                        )
                    .prepareNext()
                    .way()
                        .amenity("parking")
                        .tagNot("access", "private")
                        .boundingBox(
                                47.48047027491862, 19.039797484874725,
                                47.51331674014172, 19.07404761761427
                        )
                    .prepareNext()
                    .rel()
                        .amenity("parking")
                        .tagNot("access", "private")
                        .boundingBox(
                                47.48047027491862, 19.039797484874725,
                                47.51331674014172, 19.07404761761427
                        )
                .end()
                .output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 100)
                .build()
        ;

        String expected = "[out:\"json\"][timeout:\"30\"]; ("
                + "node[\"amenity\"=\"parking\"][\"access\"!=\"private\"](47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427); "
                + "way[\"amenity\"=\"parking\"][\"access\"!=\"private\"](47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427); "
                + "rel[\"amenity\"=\"parking\"][\"access\"!=\"private\"](47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427)"
                + ";<;); out body center qt 100;";

        assertEquals(expected, result);
    }

    @Test
    public void testMultipleFilterQueriesWithGlobalBoundingBoxSearch() throws Exception {
        String result = new OverpassQuery()
                .format(JSON)
                .timeout(30)
                .boundingBox(
                        47.48047027491862, 19.039797484874725,
                        47.51331674014172, 19.07404761761427
                )
                .filterQuery()
                    .node()
                        .amenity("parking")
                        .tagNot("access", "private")
                    .prepareNext()
                    .way()
                        .amenity("parking")
                        .tagNot("access", "private")
                .end()

                .output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 100)
                .build()
                ;

        String expected = "[out:\"json\"][timeout:\"30\"]"
                + "[bbox:47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427]"
                + "; ("
                + "node[\"amenity\"=\"parking\"][\"access\"!=\"private\"]; "
                + "way[\"amenity\"=\"parking\"][\"access\"!=\"private\"]"
                + ";<;); out body center qt 100;";

        assertEquals(expected, result);
    }
}