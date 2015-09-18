package hu.supercluster.overpasser.app.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import hu.supercluster.overpasser.R;
import hu.supercluster.overpasser.adapter.OverpassQueryResult;
import hu.supercluster.overpasser.adapter.OverpassQueryResult.Element;
import hugo.weaving.DebugLog;

@EBean(scope = EBean.Scope.Singleton)
public class PoiInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Map<String, OverpassQueryResult.Element> map;

    @RootContext
    Context context;

    public PoiInfoWindowAdapter() {
        map = new HashMap<>();
    }

    public void addMarkerInfo(Marker marker, OverpassQueryResult.Element poi) {
        map.put(marker.getId(), poi);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    @DebugLog
    public View getInfoContents(Marker marker) {
        Element poi = getElement(marker);

        if (poi != null) {
            return createView(poi);

        } else {
            return null;
        }
    }

    private Element getElement(Marker marker) {
        return map.get(marker.getId());
    }

    @NonNull
    private View createView(Element poi) {
        PoiInfoWindowView view = PoiInfoWindowView_.build(context);
        view.setTitle(poi.tags.name);
        view.setBody(getPoiDescription(poi));

        return view;
    }

    String getPoiDescription(Element poi) {
        StringBuilder builder = new StringBuilder();
        Element.Tags info = poi.tags;

        builder.append(getLine(getAddressLine1(info)));
        builder.append(getLine(getAddressLine2(info)));
        builder.append(getLine(info.phone));
        builder.append(getLine(info.website));
        builder.append("\n");
        builder.append(getLine(info.wheelchairDescription));
        builder.append(getLine(getFeeInfo(info)));

        return builder.toString().trim();
    }

    private String getFeeInfo(Element.Tags info) {
        return getBooleanInfo(info.fee, R.string.poi_info_payant);
    }

    @Nullable
    private String getBooleanInfo(String info, int label) {
        if (info != null) {
            Resources resources = context.getResources();
            int i = mapChoice(info);

            return String.format("%s: %s",
                    resources.getString(label),
                    i > 0 ? resources.getString(i) : info
            );

        } else {
            return null;
        }
    }

    private String getAddressLine1(Element.Tags info) {
        return info.addressCity;
    }

    private String getAddressLine2(Element.Tags info) {
        StringBuilder builder = new StringBuilder();

        if (info.addressStreet != null) {
            builder.append(info.addressStreet);
            builder.append(" ");
        }

        if (info.addressHouseNumber != null) {
            builder.append(info.addressHouseNumber);
            builder.append(".");
        }

        return builder.toString().trim();
    }

    String getLine(String text) {
        return text == null ? "" : text + "\n";
    }

    private int mapChoice(String choice) {
        switch (choice.toLowerCase()) {
            case "yes":
            case "designated":
                return R.string.yes;

            case "limited":
                return R.string.limited;

            case "no":
                return R.string.no;

            default:
                return 0;
        }
    }
}
