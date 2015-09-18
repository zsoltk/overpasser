package hu.supercluster.overpasser.app.view;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import hu.supercluster.overpasser.R;
import hugo.weaving.DebugLog;

@EViewGroup(R.layout.poi_info_window)
public class PoiInfoWindowView extends LinearLayout {
    @ViewById
    TextView title;

    @ViewById
    TextView body;

    @ViewById
    TextView url;

    public PoiInfoWindowView(Context context) {
        super(context);
    }

    public PoiInfoWindowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PoiInfoWindowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @DebugLog
    public void setTitle(String title) {
        this.title.setText(title);
        this.title.setVisibility(VISIBLE);
    }

    @DebugLog
    public void setBody(String body) {
        this.body.setText(body);
    }

    public void setUrl(String url) {
        if (url != null) {
            if (!url.startsWith("http://")) {
                url = "http://" + url;
            }

            this.url.setText(url);
            this.url.setMovementMethod(LinkMovementMethod.getInstance());
            this.url.setVisibility(VISIBLE);

        } else {
            this.url.setVisibility(GONE);
        }
    }
}
