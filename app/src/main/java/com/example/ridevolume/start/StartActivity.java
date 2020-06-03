package com.example.ridevolume.start;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ridevolume.R;
import com.example.ridevolume.location.LocationUtils;
import com.example.ridevolume.settings.SettingsActivity;
import com.example.ridevolume.speedometer.SpeedometerActivity;

public class StartActivity extends AppCompatActivity {

    private Button mStartBtn, mSettingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (LocationUtils.getRequestingLocationUpdates(this))
            switchToSpeedometer();

        mStartBtn = findViewById(R.id.start_btn);
        mStartBtn.setOnClickListener(v -> switchToSpeedometer());

        mSettingsBtn = findViewById(R.id.settings_btn);
        mSettingsBtn.setOnClickListener(v -> switchToSettings());
    }

    private void switchToSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void switchToSpeedometer() {
        Intent intent = new Intent(this, SpeedometerActivity.class);
        startActivity(intent);
    }

}
