package hu.supercluster.overpassapiquery;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import hu.supercluster.util.LocationHelper;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;

@EBean
public class MapUiHandler {
    private MapFragment fragment;

    @Bean
    LocationHelper locationHelper;

    public void setFragment(MapFragment fragment) {
        this.fragment = fragment;
    }

    void setMapParams() {
        GoogleMap googleMap = fragment.getGoogleMap();
        googleMap.setMapType(MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.setOnCameraChangeListener(getOnCameraChangeListener());
        resetMap();
    }

    public void resetMap() {
        GoogleMap googleMap = fragment.getGoogleMap();
        if (googleMap != null) {
            googleMap.clear();
        }
    }

    public void moveMapToCurrentPosition() {
        moveMap(locationHelper.getLastKnownLocation());
    }

    void moveMap(Location location) {
        if (location != null) {
            CameraUpdate cameraUpdate = getCameraUpdate(location);
            fragment.getGoogleMap().moveCamera(cameraUpdate);
        }
    }

    private CameraUpdate getCameraUpdate(Location location) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(fragment.zoomLevel)
                .build()
        ;

        return CameraUpdateFactory.newCameraPosition(cameraPosition);
    }

    private GoogleMap.OnCameraChangeListener getOnCameraChangeListener() {
        return new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                fragment.zoomLevel = cameraPosition.zoom;
            }
        };
    }
}
