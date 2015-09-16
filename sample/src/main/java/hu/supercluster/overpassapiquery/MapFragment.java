package hu.supercluster.overpassapiquery;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.androidannotations.annotations.EFragment;

@EFragment
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {
    private GoogleMap googleMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
