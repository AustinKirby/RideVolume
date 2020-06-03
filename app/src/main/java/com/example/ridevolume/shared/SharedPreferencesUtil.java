package com.example.ridevolume.shared;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

/**
 * SharedPreferencesUtil is a utility class for the application's shared preferences
 *
 * @author Austin Kirby
 */
public class SharedPreferencesUtil {

    private static final String SHARED_PREFERENCES = "com.example.ridevolume.settings";
    private static final int SHARED_PREFERENCES_MODE = Context.MODE_PRIVATE;

    /**
     * getString is used to get a string by the given
     * key within the application's shared preferences
     * when the default value is an empty string.
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(@NotNull Context context, @NotNull String key) {
        return getString(context, key, "");
    }

    /**
     * getString is used to get a string by the given
     * key within the application's shared preferences.
     *
     * @param context Context
     * @param key String
     * @param defaultValue String
     * @return String value within shared preferences
     */
    public static String getString(@NotNull Context context, @NotNull String key, @NotNull String defaultValue) {
        return getSharedPreferences(context).getString(key, defaultValue);
    }


    /**
     * putString is used to set a string by the given
     * key within the application's shared preferences.
     *
     * @param context Context
     * @param key String
     * @param value String
     */
    public static void putString(@NotNull Context context, @NotNull String key, @NotNull String value) {
        getSharedPreferences(context).edit().putString(key, value).apply();
    }


    /**
     * getBoolean is used to get a boolean by the given
     * key within the application's shared preferences
     *
     * @param context Context
     * @param key String
     * @param defaultValue boolean
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * putBoolean is used to set a boolean by the given
     * key within the application's shared preferences
     *
     * @param context Context
     * @param key String
     * @param defaultValue boolean
     */
    public static void putBoolean(Context context, String key, boolean defaultValue) {
        getSharedPreferences(context).edit().putBoolean(key, defaultValue).apply();
    }

    /**
     * getSharedPreferences is a utility function to get the
     * applications shared preferences with the final class values.
     *
     * @param context Context
     * @return SharedPreferences
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES, SHARED_PREFERENCES_MODE);
    }
}
