package com.example.footballplayer;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;


public class SettingFragment extends PreferenceFragmentCompat {
    // Create a class called SettingsFragment that extends PreferenceFragmentCompat

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // In SettingsFragment's onCreatePreferences method add the preference file using the
        // addPreferencesFromResource method

        // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
