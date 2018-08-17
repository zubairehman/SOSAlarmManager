package com.cubivue.base.util.preference.preferenceActivity.builder;

import android.content.Context;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceCategory;

import com.cubivue.base.util.preference.preferenceActivity.fragment.NewPreferenceHeaderFragment;

import de.mrapp.android.preference.activity.NavigationPreference;

public class PreferenceBuilder {

    //Declare singleton instance
    private static PreferenceBuilder mInstance;

    private PreferenceBuilder() {
    }

    public static synchronized void initializeInstance() {
        if (mInstance == null) {
            mInstance = new PreferenceBuilder();
        }
    }

    public static synchronized PreferenceBuilder getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException(PreferenceBuilder.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return mInstance;
    }

    //General Methods:------------------------------------------------------------------------------
    public PreferenceCategory createPreferenceCategory(Context context, String title) {
        PreferenceCategory preferenceCategory = new android.support.v7.preference.PreferenceCategory(context);
        preferenceCategory.setTitle(title);

        return preferenceCategory;
    }

    public CheckBoxPreference createCheckBoxPreference(Context context, String key, String title, String summary) {
        CheckBoxPreference preference = new CheckBoxPreference(context);
        preference.setKey(key);
        preference.setTitle(title);
        preference.setFragment(NewPreferenceHeaderFragment.class.getName());
        preference.setSummary(summary);

        return preference;
    }

    public SwitchPreference createSwitchPreference(Context context, String key, String title, String summary) {
        SwitchPreference preference = new SwitchPreference(context);
        preference.setKey(key);
        preference.setTitle(title);
        preference.setFragment(NewPreferenceHeaderFragment.class.getName());
        preference.setSummary(summary);

        return preference;
    }

    public EditTextPreference createEditTextPreference(Context context, String key, String title, String summary) {
        EditTextPreference preference = new EditTextPreference(context);
        preference.setKey(key);
        preference.setTitle(title);
        preference.setFragment(NewPreferenceHeaderFragment.class.getName());
        preference.setSummary(summary);

        return preference;
    }

    public ListPreference createListPreference(Context context, String key, String title, String summary) {
        ListPreference preference = new ListPreference(context);
        preference.setKey(key);
        preference.setTitle(title);
        preference.setFragment(NewPreferenceHeaderFragment.class.getName());
        preference.setSummary(summary);

        return preference;
    }

    public NavigationPreference createNavigationPreference(Context context, String title) {
        NavigationPreference navigationPreference = new NavigationPreference(context);
        navigationPreference.setTitle(title);
        navigationPreference.setFragment(NewPreferenceHeaderFragment.class.getName());

        return navigationPreference;
    }

}
