package hu.supercluster.overpasser.app.util;

import android.location.Location;
import android.location.LocationManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

@EBean
public class LocationHelper {
    @SystemService
    LocationManager locationManager;

    public Location getLastKnownLocation() {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

        return location;
    }
}
