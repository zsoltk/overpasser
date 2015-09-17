package hu.supercluster.overpassapiquery.app.activity.container;

import com.google.android.gms.maps.model.LatLngBounds;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import hu.supercluster.overpassapiquery.adapter.OverpassQueryResult;
import hu.supercluster.overpassapiquery.library.query.OverpassQuery;
import hu.supercluster.overpassapiquery.adapter.OverpassServiceProvider;

import static hu.supercluster.overpassapiquery.library.output.OutputFormat.JSON;

@EBean
public class MapOverpassAdapter {
    @Bean
    OverpassServiceProvider serviceProvider;

    public OverpassQueryResult search(final LatLngBounds bounds) {
        OverpassQuery query = new OverpassQuery()
                .format(JSON)
                .timeout(30)
                .mapQuery()
                    .nodes()
                    .amenity("toilets")
                    .notEquals("access", "private")
                    .boundingBox(
                            bounds.southwest.latitude,
                            bounds.southwest.longitude,
                            bounds.northeast.latitude,
                            bounds.northeast.longitude
                    )
                .end()
                .output(100)
        ;

        return interpret(query.build());
    }

    private OverpassQueryResult interpret(String query) {
        try {
            return serviceProvider.get().interpreter(query);

        } catch (Exception e) {
            e.printStackTrace();

            return new OverpassQueryResult();
        }
    }
}