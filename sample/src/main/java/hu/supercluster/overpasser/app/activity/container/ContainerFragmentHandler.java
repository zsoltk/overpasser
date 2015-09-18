package hu.supercluster.overpasser.app.activity.container;

import android.support.v4.app.FragmentActivity;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import hu.supercluster.overpasser.app.util.AbstractFragmentHandler;

@EBean
class ContainerFragmentHandler extends AbstractFragmentHandler {
    private MapFragment mapFragment;

    @RootContext
    ContainerActivity activity;

    @Override
    protected FragmentActivity getFragmentActivity() {
        return activity;
    }

    final MapFragment getMapFragment() {
        if (mapFragment == null) {
            mapFragment = getFragment(MapFragment_.class, MapFragment_.builder().build());
        }

        return mapFragment;
    }
}
