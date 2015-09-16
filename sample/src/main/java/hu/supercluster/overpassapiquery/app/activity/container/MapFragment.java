package hu.supercluster.overpassapiquery.app.activity.container;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;

@EFragment
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {
    private GoogleMap googleMap;

    @InstanceState
    float zoomLevel = 15;

    @Bean
    MapUiHandler uiHandler;

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @AfterInject
    void init() {
        uiHandler.setFragment(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        uiHandler.setMapParams();
        uiHandler.moveMapToCurrentPosition();
    }
}
