package com.example.ridevolume.location;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.ridevolume.R;
import com.example.ridevolume.shared.SharedPreferencesUtil;
import com.example.ridevolume.speedometer.SpeedometerActivity;
import com.example.ridevolume.start.StartActivity;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.Date;


/**
 * LocationUtils is a utility class for location functionality.
 *
 * @author Austin Kirby
 */
public class LocationUtils {

    private final static String TAG = LocationUtils.class.getSimpleName();

    private final static String LOCATION_NAME = "location";

    private final static String CHANNEL_ID = "channel_01";

    /**
     * getRequestingLocationUpdates is used to update the
     * application's shared preferences flag for
     * whether the application is currently getting
     * location updates.
     *
     * @param context {@link Context}
     * @return boolean
     */
    public static boolean getRequestingLocationUpdates(@NotNull Context context) {
        return SharedPreferencesUtil.getBoolean(context, LOCATION_NAME, false);
    }

    /**
     * setRequestingLocationUpdates is used to set
     * whether the application is currently getting
     * location updates.
     *
     * @param context {@link Context}
     * @param value boolean
     */
    public static void setRequestingLocationUpdates(@NotNull Context context, @NotNull boolean value) {
        Log.i(TAG, "Get requesting location: " + ((value) ? "true":"false"));
        SharedPreferencesUtil.putBoolean(context, LOCATION_NAME, value);
    }

    /**
     * getLocationText returns the {@code location} object as a human readable string.
     * @param location  The {@link Location}.
     */
    static String getLocationText(Location location) {
        Log.i(TAG, "Location: {'speed':" + location.getSpeed() * 3.6 + "}");
        return location == null ? "Unknown location" :
                "{'speed':" + location.getSpeed() + "}";
    }

    /**
     * getLocationTitle
     *
     * @param context {@link Context}
     * @return String
     */
    @SuppressLint("StringFormatInvalid")
    static String getLocationTitle(@NotNull Context context) {
        return context.getString(R.string.location_updated,
                DateFormat.getDateTimeInstance().format(new Date()));
    }

    /**
     * Posts a notification in the notification bar when a transition is detected.
     * If the user clicks the notification, control goes to the MainActivity.
     */
    static void sendNotification(Context context, Location location) {
        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(context, StartActivity.class);

        notificationIntent.putExtra("from_notification", true);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(StartActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        // Define the notification settings.
        builder.setSmallIcon(R.mipmap.ic_launcher)
                // In a real app, you may want to use a library like Volley
                // to decode the Bitmap.
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher))
                .setColor(Color.RED)
                .setContentTitle("Location update")
                .setContentText("{'speed':" + (int) (location.getSpeed() * 3.6) + "}")
                .setContentIntent(notificationPendingIntent);

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(true);

        // Only vibrate once
        builder.setOnlyAlertOnce(true);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.app_name);
            // Create the channel for the notification
            NotificationChannel mChannel =
                    new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);

            // Set the Notification Channel for the Notification Manager.
            mNotificationManager.createNotificationChannel(mChannel);

            // Channel ID
            builder.setChannelId(CHANNEL_ID);
        }

        // Issue the notification
        mNotificationManager.notify(0, builder.build());
    }
}
