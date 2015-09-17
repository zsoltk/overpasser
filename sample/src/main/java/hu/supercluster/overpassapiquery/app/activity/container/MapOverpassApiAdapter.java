package hu.supercluster.overpassapiquery.app.activity.container;

import com.google.android.gms.maps.model.LatLngBounds;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import hu.supercluster.overpassapiquery.library.query.OverApiQuery;
import hu.supercluster.overpassapiquery.adapter.OverApiResult;
import hu.supercluster.overpassapiquery.adapter.OverPassServiceProvider;

import static hu.supercluster.overpassapiquery.library.outputparam.OverPassOutputFormat.JSON;

@EBean
public class MapOverpassApiAdapter {
    @Bean
    OverPassServiceProvider serviceProvider;

    public OverApiResult search(final LatLngBounds bounds) {
        OverApiQuery query = new OverApiQuery()
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

    private OverApiResult interpret(String query) {
        try {
            return serviceProvider.get().interpreter(query);

        } catch (Exception e) {
            e.printStackTrace();

            return new OverApiResult();
        }
    }
}