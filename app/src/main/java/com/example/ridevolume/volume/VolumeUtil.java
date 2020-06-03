package com.example.ridevolume.volume;

import android.content.Context;
import android.media.AudioManager;

/**
 * VolumeUtil is a volume utility class
 *
 * @author Austin Kirby
 */
public class VolumeUtil {

    /**
     * getMaxVolume returns the max volume from STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     * @return int
     */
    public static int getMaxVolume(Context context) {
        return getAudioManager(context).getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * getMinVolume returns the min volume from STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     * @return int
     */
    public static int getMinVolume(Context context) {
        return getAudioManager(context).getStreamMinVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * getVolume returns the current volume level from STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     * @return int
     */
    public static int getVolume(Context context) {
        return getAudioManager(context).getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * raiseVolume raises the current volume from STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     */
    public static void raiseVolume(Context context) {
        getAudioManager(context)
            .adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * muteVolume mutes the current volume from STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     */
    public static void muteVolume(Context context) {
        getAudioManager(context)
            .adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * lowerVolume lowers the current volume from STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     */
    public static void lowerVolume(Context context) {
        getAudioManager(context)
            .adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * setVolume sets the current volume to a given int
     * for the STREAM_MUSIC channel.
     *
     * @param context {@link Context}
     * @param volume int
     */
    public static void setVolume(Context context, int volume) {
        if (volume < getMinVolume(context) || volume > getMaxVolume(context))
            return;

        getAudioManager(context)
            .setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * getAudioManager is a helper method to get the Audio Manager
     *
     * @param context {@link Context}
     * @return {@link AudioManager}
     */
    private static AudioManager getAudioManager(Context context) {
        return ((AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE));
    }
}
