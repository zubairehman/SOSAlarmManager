/*
 * Copyright 2014 - 2018 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cubivue.base.util.preference.preferenceActivity.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;

import com.cubivue.base.R;
import com.cubivue.base.util.preference.preferenceActivity.builder.PreferenceBuilder;
import com.cubivue.base.util.preference.preferenceActivity.fragment.NewPreferenceHeaderFragment;

import de.mrapp.android.preference.activity.NavigationPreference;
import de.mrapp.android.preference.activity.PreferenceActivity;

/**
 * An activity, which is used to demonstrate a {@link PreferenceActivity}, whose headers can be
 * created or removed dynamically at runtime.
 *
 * @author Zubair Rehman
 */
public class BaseSettingsActivity extends AbstractPreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = BaseSettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceBuilder.initializeInstance();
    }

    @Override
    protected void onCreateNavigation(@NonNull final PreferenceFragmentCompat fragment) {
        fragment.addPreferencesFromResource(R.xml.dynamic_navigation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNavigationFragment().getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getNavigationFragment().getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i(TAG, "onSharedPreferenceChanged: " + key);
    }

    /**
     * Dynamically adds a new navigation preference to the activity.
     */
    public void addNavigationPreference() {

        //adding category

        //adding preference
        SwitchPreference infoMessagePref = new SwitchPreference(this);
        infoMessagePref.setTitle(getPreferenceHeaderTitle());
        infoMessagePref.setKey("info");
        infoMessagePref.setFragment(NewPreferenceHeaderFragment.class.getName());
        infoMessagePref.setSummary("Test Summary");

        CheckBoxPreference warningMessagePref = new CheckBoxPreference(this);
        warningMessagePref.setTitle(getPreferenceHeaderTitle());
        warningMessagePref.setKey("warnindg");
        warningMessagePref.setFragment(NewPreferenceHeaderFragment.class.getName());
        warningMessagePref.setSummary("Test Summary");

        EditTextPreference preference = new EditTextPreference(this);
        preference.setTitle(getPreferenceHeaderTitle());
        preference.setKey("warningw");
        preference.setFragment(NewPreferenceHeaderFragment.class.getName());
        preference.setSummary("Test Summary");

        //adding pref to screen
        addPreference(PreferenceBuilder.getInstance().createPreferenceCategory(this, "Info Message"));
//        addPreference(PreferenceBuilder.getInstance().createSwitchPreference(this, "Info Message"));
        addPreference(warningMessagePref);
        addPreference(preference);
    }

    public void addPreference(Preference preference) {
        getNavigationFragment().getPreferenceScreen().addPreference(preference);
        invalidateOptionsMenu();
    }

    /**
     * Removes all navigation preferences from the activity.
     */
    private void clearNavigationPreference() {
        PreferenceScreen preferenceScreen = getNavigationFragment().getPreferenceScreen();
        preferenceScreen.removeAll();
    }

    /**
     * Returns an unique title, which can be used for a new preference header.
     *
     * @return The title as an instance of the class {@link CharSequence}
     */
    public CharSequence getPreferenceHeaderTitle() {
        CharSequence originalTitle = getText(R.string.new_navigation_preference_title);
        CharSequence title = originalTitle;
        int counter = 1;

        while (isTitleAlreadyUsed(title)) {
            title = originalTitle + " (" + counter + ")";
            counter++;
        }

        return title;
    }

    /**
     * Returns, whether a preference header, which has a specific title, has already been added to
     * the activity, or not.
     *
     * @param title The title, whose presence should be checked, as an instance of the class {@link
     *              CharSequence}. The tile may not be null
     * @return True, if a preference header, which has the given title, has already been added to
     * the activity, false otherwise
     */
    private boolean isTitleAlreadyUsed(@NonNull final CharSequence title) {
        for (NavigationPreference navigationPreference : getAllNavigationPreferences()) {
            if (title.equals(navigationPreference.getTitle())) {
                return true;
            }
        }

        return false;
    }

}