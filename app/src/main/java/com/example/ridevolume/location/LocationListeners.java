package com.example.ridevolume.location;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationListeners {

    private static final String TAG = LocationListeners.class.getSimpleName();

    public interface Callback {
        void onLocationChange(Location location);
    }

    private List<Callback> mCallbacks;
    private static LocationListeners mInstance;


    private LocationListeners() { }

    public static LocationListeners getInstance() {
        if (mInstance == null)
            mInstance = new LocationListeners();
        return mInstance;
    }

    public void setOnLocationChange(Callback callback) {
        if (mInstance.mCallbacks == null)
            mInstance.mCallbacks = new ArrayList<>();
        mInstance.mCallbacks.add(callback);
    }

    void newLocation(Location location) {
        notifyListeners(location);
    }

    private void notifyListeners(Location location) {
        if (mInstance.mCallbacks == null) return;
        Log.i(TAG, "Notify Listeners: " + location.toString());
        mInstance.mCallbacks.forEach(c -> c.onLocationChange(location));
    }

}
