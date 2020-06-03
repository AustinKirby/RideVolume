package com.example.ridevolume.services;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.ridevolume.location.LocationListeners;
import com.example.ridevolume.location.LocationReceiver;
import com.example.ridevolume.location.LocationService;
import com.example.ridevolume.settings.SettingsUtil;
import com.example.ridevolume.settings.Setting;
import com.example.ridevolume.volume.VolumeUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.util.Collections.sort;

/**
 * RideVolumeService is used to watch {@link LocationService} updates
 * than change the volume using {@link VolumeUtil} depending of
 * the {@link Setting} values.
 *
 * @author Austin Kirby
 */
public class RideVolumeService {

    private static final String TAG = RideVolumeService.class.getSimpleName();

    private Context mContext;

    /**
     * RideVolumeService constructor
     * @param context {@link Context}
     */
    public RideVolumeService(@NotNull Context context) {
        mContext = context;
    }


    /**
     * updateVolume takes a given speed and looks for the
     * proper setting then updates the volume based of off
     * the setting.
     *
     * @param speed int
     */
    public void updateVolume(int speed) {
        ArrayList<Setting> settings = new ArrayList<>(SettingsUtil.loadSettings(mContext));
        sort(settings, (o1, o2) -> {
            if (o1.getSpeed() > o2.getSpeed())
                return 1;
            else if (o1.getSpeed() == o2.getSpeed())
                return 0;
            else
                return -1;
        });

        int volume = 0;
        for (int i = 0; i < settings.size(); i++) {
            if (settings.get(i).getSpeed() < speed)
                volume = settings.get(i).getVolumeLevel();
            else
                break;
        }

        Log.i(TAG, "{'speed':" + speed + ", 'volume':" + volume + "}");
        VolumeUtil.setVolume(mContext, volume);
    }
}
