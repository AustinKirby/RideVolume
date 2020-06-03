package com.example.ridevolume.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ridevolume.R;

import java.util.ArrayList;
import java.util.List;

/**
 * SettingsAdapter
 *
 * @author Austin Kirby
 */
public class SettingsAdapter extends ArrayAdapter<Setting> {

    private ArrayList<Setting> mSettings;

    /**
     * SettingsAdapter constructor
     * @param context {@link Context}
     * @param resource int
     * @param settings List of settings
     */
    public SettingsAdapter(@NonNull Context context, int resource, List<Setting> settings) {
        super(context, resource, settings);
        mSettings = new ArrayList<>(settings);
    }

    @Override
    public int getCount() {
        return mSettings.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.fragment_settings, null);

        TextView speedValue = (TextView) v.findViewById(R.id.speed_value);
        speedValue.setText(String.valueOf(mSettings.get(position).getSpeed()));

        TextView volumeValue = (TextView) v.findViewById(R.id.volume_value);
        volumeValue.setText(String.valueOf(mSettings.get(position).getVolumeLevel()));

        return v;
    }
}
