package com.example.ridevolume.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ridevolume.R;

/**
 * SettingsActivity
 *
 * @author Austin Kirby
 */
public class SettingsActivity extends AppCompatActivity {

    private ListView mSettingsList;

    private Button mBackBtn, mNewBtn;

    private SettingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(v -> finish());

        mNewBtn = findViewById(R.id.new_btn);
        mNewBtn.setOnClickListener(v -> pushNewModal());

        mSettingsList = (ListView) findViewById(R.id.settings_list);
        updateList();
    }

    /**
     * updateList is used to inflate the
     * settings list with {@link Setting}s.
     */
    private void updateList() {
        mAdapter = new SettingsAdapter(
                getApplicationContext(),
                R.layout.fragment_settings,
                SettingsUtil.loadSettings(this)
        );
        mSettingsList.setAdapter(mAdapter);
    }

    /**
     * pushNewModal is used to open {@link NewSettingFragment}.
     */
    private void pushNewModal() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        NewSettingFragment newSettingFragment = new NewSettingFragment(setting -> updateList());

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.add(android.R.id.content, newSettingFragment)
                .addToBackStack(null).commit();
    }
}
