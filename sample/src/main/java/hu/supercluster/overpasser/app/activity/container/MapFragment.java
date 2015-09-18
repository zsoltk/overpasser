package hu.supercluster.overpasser.app.activity.container;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;

import hu.supercluster.overpasser.app.view.TouchableWrapper;

@EFragment
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, TouchableWrapper.Callbacks {
    public static final int TOUCH_TIMEOUT = 400;
    private GoogleMap googleMap;
    private CountDownTimer touchTimeoutTimer;

    @InstanceState
    float zoomLevel = 15;

    @Bean
    MapUiHandler uiHandler;

    @Bean
    MapPoiHandler poiHandler;

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @AfterInject
    void init() {
        uiHandler.setFragment(this);
        poiHandler.setFragment(this);
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
        searchPois();
    }

    private void searchPois() {
        poiHandler.fetchPois(getLatLngBounds());
    }

    LatLngBounds getLatLngBounds() {
        return googleMap.getProjection().getVisibleRegion().latLngBounds;
    }

    @Override
    public void onWrapperTouchStart() {
        if (touchTimeoutTimer != null) {
            touchTimeoutTimer.cancel();
        }
    }

    @Override
    public void onWrapperTouchReleased() {
        touchTimeoutTimer = getTouchTimeoutCountDownTimer();
        touchTimeoutTimer.start();
    }

    @NonNull
    private CountDownTimer getTouchTimeoutCountDownTimer() {
        return new CountDownTimer(TOUCH_TIMEOUT, TOUCH_TIMEOUT) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                searchPois();
            }
        };
    }
}
