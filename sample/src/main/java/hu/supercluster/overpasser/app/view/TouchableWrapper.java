package hu.supercluster.overpasser.app.view;

import android.content.Context;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public  class TouchableWrapper extends FrameLayout {
    private long lastTouched = 0;
    private Callbacks callbacks;
    private final long thresholdMs;

    public TouchableWrapper(Context context, Callbacks callbacks) {
        this(context, callbacks, 100);
    }

    public TouchableWrapper(Context context, Callbacks callbacks, long thresholdMs) {
        super(context);
        this.callbacks = callbacks;
        this.thresholdMs = thresholdMs;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        callbackIfNeeded(event);

        return super.dispatchTouchEvent(event);
    }

    private void callbackIfNeeded(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouched = SystemClock.uptimeMillis();
                callbacks.onWrapperTouchStart();
                break;

            case MotionEvent.ACTION_UP:
                if (SystemClock.uptimeMillis() - lastTouched > thresholdMs) {
                    callbacks.onWrapperTouchReleased();
                }

                break;
        }
    }

    public interface Callbacks {
        public void onWrapperTouchStart();
        public void onWrapperTouchReleased();
    }
}