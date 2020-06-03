package com.example.ridevolume.settings;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SettingTest {
    @Test
    public void testSettingConstructor() {

        // Handle empty constructor
        Setting setting = new Setting();
        assert setting != null;
        assertEquals(setting.getSpeed(), 0);
        assertEquals(setting.getVolumeLevel(), 0);

        // Handle negative values
        setting = new Setting(-1, -1);
        assert setting != null;
        assertEquals(setting.getSpeed(), 0);
        assertEquals(setting.getVolumeLevel(), 0);

        // Handle outside values
        setting = new Setting(-1, 101);
        assertEquals(setting.getSpeed(), 0);
        assertEquals(setting.getVolumeLevel(), 100);

        // Handle normal values
        setting = new Setting(55, 20);
        assert setting != null;
        assertEquals(setting.getSpeed(), 55);
        assertEquals(setting.getVolumeLevel(), 20);
    }
    @Test
    public void testFromJSON() {
        try {
            // Handle empty string
            Setting setting = Setting.FromJSON("");
            assertNull(setting);

            // Handle normal values
            setting = Setting.FromJSON("{'speed':'55','volumeLevel':'20'}");
            assert setting != null;
            assertEquals(setting.getSpeed(), 55);
            assertEquals(setting.getVolumeLevel(), 20);


            // Handle negative values
            setting = Setting.FromJSON("{'speed':'-1','volumeLevel':'-1'}");
            assert setting != null;
            assertEquals(setting.getSpeed(), 0);
            assertEquals(setting.getVolumeLevel(), 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testToJSON() {
        // Handle normal values
        Setting setting = new Setting(55, 20);
        assertEquals(setting.toJSON(), "{'speed':'55','volumeLevel':'20'}");

        // Check cross functionality
        try {
            Setting s1 = Setting.FromJSON(setting.toJSON());
            assertEquals(s1.getSpeed(), 55);
            assertEquals(s1.getVolumeLevel(), 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testIsEqualTo() {
        // Handle true
        Setting s1 = new Setting(55, 20);
        Setting s2 = new Setting(55, 20);
        assertTrue(s1.isEqualTo(s2));

        // Handle false
        s2 = new Setting(30, 50);
        assertFalse(s1.isEqualTo(s2));

        // Handle overlap
        s2 = new Setting(55, 50);
        assertFalse(s1.isEqualTo(s2));

        s2 = new Setting(30, 20);
        assertFalse(s1.isEqualTo(s2));
    }
}
