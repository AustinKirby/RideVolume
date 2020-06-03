package com.example.ridevolume.settings;

import android.content.Context;
import android.util.Log;

import com.example.ridevolume.shared.SharedPreferencesUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SettingsUtil is a utility class for saving
 * and loading settings from Shared Preferences
 *
 * @author Austin Kirby
 */
public class SettingsUtil {

    private static final String TAG = SettingsUtil.class.getSimpleName();

    private static final String SETTINGS_NAME = "settings";

    /**
     * loadSettings is used to load settings
     * from the applications Shared Preferences.
     *
     * @param context Context
     * @return List of Settings
     */
    public static List<Setting> loadSettings(@NotNull  Context context) {
        try {
            return settingsFromJSON(
                    SharedPreferencesUtil.getString(context, SETTINGS_NAME)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    /**
     * saveSettings is used to save settings
     * to the applications Shared Preferences.
     *
     * @param context Context
     * @param settings List of Settings
     */
    public static void saveSettings(@NotNull Context context, @NotNull List<Setting> settings) {
        SharedPreferencesUtil.putString(context, SETTINGS_NAME, settingsToJSON(settings));
    }

    /**
     * settingsToJSON takes a list of settings and
     * returns a JSON string of the list.
     *
     * @param settings List of Settings
     * @return String
     */
    private static String settingsToJSON(@NotNull List<Setting> settings) {
        String str = "[";
        for (int i = 0; i < settings.size(); i ++) {
            str += settings.get(i).toJSON();
            if (i < settings.size() - 1)
                str += ",";
        }
        str += "]";
        Log.i(TAG, "TO JSON: " + str);
        return str;
    }

    /**
     * settingsFromJSON takes a JSON string and
     * returns a List of settings.
     *
     * @param json String
     * @return List of Settings
     * @throws JSONException
     */
    private static List<Setting> settingsFromJSON(@NotNull String json) throws JSONException {
        Log.i(TAG, "From JSON: " + json);
        JSONArray arr = new JSONArray(json);
        List<Setting> list = new ArrayList<Setting>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(Setting.FromJSON(arr.getString(i)));
        }
        return list;
    }
}
