package com.example.ridevolume.settings;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Setting model for handling user specified
 * speed to volume level settings
 *
 * @author Austin Kirby
 */
public class Setting {

    private int mSpeed;
    private int mVolumeLevel;

    /**
     * Setting empty constructor, by default
     * speed and volume level will be 0
     */
    public Setting() {
        this(0, 0);
    }

    /**
     * Setting constructor with given speed and volume level
     * @param speed int
     * @param volumeLevel int
     */
    public Setting(int speed, int volumeLevel) {
        setSpeed(speed);
        setVolumeLevel(volumeLevel);
    }

    /**
     * FromJSON factory will construct a Setting
     * from a JSON string.
     *
     * @param json String
     * @return Setting
     * @throws JSONException
     */
    public static Setting FromJSON(String json) throws JSONException {
        if (json.equals("") || json.isEmpty())
            return null;

        JSONObject obj = new JSONObject(json);
        return new Setting(
                obj.getInt("speed"),
                obj.getInt("volumeLevel")
        );
    }

    /**
     * setSpeed setter function for speed value.
     * Floor=0
     *
     * @param speed int
     */
    private void setSpeed(int speed) {
        if (speed < 0)
            speed = 0;
        mSpeed = speed;
    }

    /**
     * getSpeed getter function for speed value
     *
     * @return int
     */
    public int getSpeed() {
        return mSpeed;
    }

    /**
     * setVolumeLevel setter function for volume level value.
     * Floor=0 Ceil=100
     *
     * @param volumeLevel int
     */
    private void setVolumeLevel(int volumeLevel) {
        if (volumeLevel < 0)
            volumeLevel = 0;
        else if (volumeLevel > 100)
            volumeLevel = 100;
        mVolumeLevel = volumeLevel;
    }

    /**
     * getVolumeLevel getter function for volume level value.
     *
     * @return int
     */
    public int getVolumeLevel() {
        return mVolumeLevel;
    }

    /**
     * isEqualTo function to check equality to another {@link Setting}.
     *
     * @param setting {@link Setting}
     * @return boolean
     */
    public boolean isEqualTo(Setting setting) {
        if (setting.getSpeed() == getSpeed() && setting.getVolumeLevel() == getVolumeLevel())
            return true;
        return false;
    }

    /**
     * toJSON function to return a JSON string of {@link Setting}.
     *
     * @return String
     */
    public String toJSON() {
        return "{\"speed\":"+getSpeed()+",\"volumeLevel\":"+getVolumeLevel()+"}";
    }
}
