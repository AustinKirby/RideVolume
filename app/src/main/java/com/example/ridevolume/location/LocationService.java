package com.example.ridevolume.location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;
import java.util.List;

public class LocationService extends IntentService {

    private static final String TAG =
            LocationService.class.getSimpleName();

    public static final String ACTION_PROCESS_UPDATES =
            TAG + ".PROCESS_UPDATES";

    public LocationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATES.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    Location location = result.getLastLocation();
                    LocationUtils.sendNotification(this, location);
                    LocationListeners.getInstance().newLocation(location);
                    Log.i(TAG, "{'speed':" + (int) (location.getSpeed() * 3.6) + "}");
                }
            }
        }
    }

}
