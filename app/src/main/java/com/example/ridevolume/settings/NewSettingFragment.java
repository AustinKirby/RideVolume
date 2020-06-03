package com.example.ridevolume.settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.ridevolume.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


/**
 * NetSettingFragment is used to create new settings
 *
 * @author Austin Kirby
 */
public class NewSettingFragment extends DialogFragment {


    /**
     * Callback interface for saving the new fragment
     */
    public interface Callback {

        /**
         * onSave is called when the save button is pressed
         */
        void onSaveSetting(Setting setting);
    }

    private Callback mCallback;

    private TextInputEditText mSpeedInput, mVolumeInput;
    private Button mCancelBtn, mSaveBtn;

    /**
     * NewSettingFragment constructor
     * @param callback {@link Callback}
     */
    NewSettingFragment(Callback callback) {
        super();
        mCallback = callback;
    }

    /**
     * onCreateView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_setting, container, false);

        mSpeedInput = v.findViewById(R.id.speed_txt);
        mVolumeInput = v.findViewById(R.id.volume_txt);

        mCancelBtn = v.findViewById(R.id.cancel_btn);
        mCancelBtn.setOnClickListener(c -> dismiss());

        mSaveBtn = v.findViewById(R.id.save_btn);
        mSaveBtn.setOnClickListener(c -> {
            try {
                save();
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: Create a visual error prompt
            }
        });

        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }


    /**
     * save tries to get speed and volume level
     * values from the dialog and creates a {@link Setting}
     * from the values then saves it to shared preferences
     * and callback with new setting value
     */
    private void save() throws Exception {
        try {
            Setting setting = new Setting(getSpeed(), getVolumeLevel());
            saveSetting(setting);
            mCallback.onSaveSetting(setting);
            dismiss();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * saveSetting tries to save {@link Setting} to
     * shared preferences. Will throw a {@link NullPointerException}
     * if getContext() is null.
     *
     * @param setting
     * @throws NullPointerException
     */
    private void saveSetting(Setting setting) throws NullPointerException{
        if (getContext() == null)
            throw new NullPointerException("getContext() can not be null");
        ArrayList<Setting> settings = new ArrayList<>(SettingsUtil.loadSettings(getContext()));
        settings.add(setting);
        SettingsUtil.saveSettings(getContext(), settings);
    }

    /**
     * getSpeed attempts to get an integer value from the dialog
     * from the speed input
     *
     * @return
     * @throws NullPointerException
     * @throws NumberFormatException
     */
    private int getSpeed() throws NullPointerException, NumberFormatException {
        if (mSpeedInput.getText() == null ||
            mSpeedInput.getText().toString().isEmpty())
            throw new NullPointerException("mSpeedInput can not be null or empty");

        return Integer.parseInt(mSpeedInput.getText().toString());
    }

    /**
     * getVolumeLevel attempts to get an integer value from the dialog
     * from the volume level input
     * @return
     * @throws NullPointerException
     * @throws NumberFormatException
     */
    private int getVolumeLevel() throws NullPointerException, NumberFormatException {
        if (mVolumeInput.getText() == null ||
            mVolumeInput.getText().toString().isEmpty())
            throw new NullPointerException("mVolumeInput can not be null or empty");

        return Integer.parseInt(mVolumeInput.getText().toString());
    }
}
