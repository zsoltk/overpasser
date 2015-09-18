package hu.supercluster.overpasser.app.activity.container;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import java.util.HashMap;
import java.util.Map;

import hu.supercluster.overpasser.adapter.OverpassQueryResult;
import hu.supercluster.overpasser.adapter.OverpassQueryResult.Element;

@EBean
public class MapPoiHandler {
    private MapFragment fragment;
    private Map<Long, Element> poiMap;

    @Bean
    MapOverpassAdapter overApiAdapter;

    public MapPoiHandler() {
        poiMap = new HashMap<>();
    }

    public void setFragment(MapFragment fragment) {
        this.fragment = fragment;
    }

    public void reset() {
        poiMap.clear();
    }

    @Background
    void fetchPois(LatLngBounds bounds) {
        OverpassQueryResult result = overApiAdapter.search(bounds);

        for (Element poi : result.elements) {
            if (!alreadyStored(poi)) {
                storePoi(poi);
                showPoi(poi);
            }
        }
    }

    private boolean alreadyStored(Element poi) {
        return poiMap.containsKey(poi.id);
    }

    private void storePoi(Element poi) {
        poiMap.put(poi.id, poi);
    }

    @UiThread
    void showPoi(Element poi) {
        MarkerOptions options = new MarkerOptions()
            .position(new LatLng(poi.lat, poi.lon))
        ;

        fragment.getGoogleMap().addMarker(options);
    }
}
