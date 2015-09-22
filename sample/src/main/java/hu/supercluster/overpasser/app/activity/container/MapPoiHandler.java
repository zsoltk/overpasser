package hu.supercluster.overpasser.app.activity.container;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.HashMap;
import java.util.Map;

import hu.supercluster.overpasser.R;
import hu.supercluster.overpasser.adapter.OverpassQueryResult;
import hu.supercluster.overpasser.adapter.OverpassQueryResult.Element;
import hu.supercluster.overpasser.app.view.PoiInfoWindowAdapter;

@EBean
public class MapPoiHandler {
    private MapFragment fragment;
    private Map<Long, Element> poiMap;

    @RootContext
    Context context;

    @Bean
    MapOverpassAdapter overApiAdapter;

    @Bean
    PoiInfoWindowAdapter poiInfoWindowAdapter;

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

        if (result != null) {
            for (Element poi : result.elements) {
                if (!alreadyStored(poi)) {
                    fixTitle(poi);
                    storePoi(poi);
                    showPoi(poi);
                }
            }
        }
    }

    private boolean alreadyStored(Element poi) {
        return poiMap.containsKey(poi.id);
    }

    private void fixTitle(Element poi) {
        Element.Tags info = poi.tags;

        if (info.name == null) {
            info.name = context.getResources().getString(R.string.poi_category_public_parking);
        }
    }

    private void storePoi(Element poi) {
        poiMap.put(poi.id, poi);
    }

    @UiThread
    void showPoi(Element poi) {
        MarkerOptions options = new MarkerOptions()
            .position(new LatLng(poi.lat, poi.lon))
        ;

        Marker marker = fragment.getGoogleMap().addMarker(options);
        poiInfoWindowAdapter.addMarkerInfo(marker, poi);
    }
}
