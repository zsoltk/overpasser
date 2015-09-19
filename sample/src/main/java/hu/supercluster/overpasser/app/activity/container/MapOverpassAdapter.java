package hu.supercluster.overpasser.app.activity.container;

import com.google.android.gms.maps.model.LatLngBounds;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import hu.supercluster.overpasser.adapter.OverpassQueryResult;
import hu.supercluster.overpasser.library.query.OverpassQuery;
import hu.supercluster.overpasser.adapter.OverpassServiceProvider;

import static hu.supercluster.overpasser.library.output.OutputFormat.JSON;

@EBean
public class MapOverpassAdapter {
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
            return OverpassServiceProvider.get().interpreter(query).execute().body();

        } catch (Exception e) {
            e.printStackTrace();

            return new OverpassQueryResult();
        }
    }
}