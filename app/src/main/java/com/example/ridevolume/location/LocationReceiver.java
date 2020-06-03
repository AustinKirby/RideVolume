package com.example.ridevolume.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;
import java.util.List;

import static com.example.ridevolume.location.LocationService.ACTION_PROCESS_UPDATES;

/**
 * LocationReceiver is used to receive location
 * changes callback
 *
 * @author Austin Kirby
 */
public class LocationReceiver extends BroadcastReceiver {

    private static final String TAG = LocationReceiver.class.getSimpleName();

    public LocationReceiver() { }

    /**
     * onReceive is used to capture location changes
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATES.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    Log.i(TAG, "Location updated.");
                    Location location = result.getLastLocation();
                    LocationUtils.sendNotification(context, location);
                    LocationListeners.getInstance().newLocation(location);
                }
            }
        }
    }
}
