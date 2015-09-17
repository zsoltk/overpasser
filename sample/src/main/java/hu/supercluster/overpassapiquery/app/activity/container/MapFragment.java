package hu.supercluster.overpassapiquery.app.activity.container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;

import hu.supercluster.overpassapiquery.app.view.TouchableWrapper;

@EFragment
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, TouchableWrapper.Callbacks {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, parent, savedInstanceState);
        TouchableWrapper touchableWrapper = new TouchableWrapper(getActivity(), this);
        touchableWrapper.addView(view);

        return touchableWrapper;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        uiHandler.setMapParams();
        uiHandler.moveMapToCurrentPosition();
    }

    @Override
    public void onWrapperTouchStart() {

    }

    @Override
    public void onWrapperTouchReleased() {
        
    }
}
