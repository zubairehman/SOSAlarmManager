package com.cubivue.app.ui.settings

import android.support.v7.preference.PreferenceFragmentCompat
import com.cubivue.app.R
import com.cubivue.base.BuildConfig
import com.cubivue.base.util.preference.preferenceActivity.activity.BaseSettingsActivity
import com.cubivue.base.util.preference.preferenceActivity.builder.PreferenceBuilder

class SettingsActivity : BaseSettingsActivity() {


    override fun onCreateNavigation(fragment: PreferenceFragmentCompat) {
        super.onCreateNavigation(fragment)
        addPreference()
    }

    /**
     * Dynamically adds a new navigation preference to the activity.
     */
    override fun addPreference() {
        super.addPreference()

        //adding pref to screen
        //default language
        addPreference(PreferenceBuilder.getInstance().createPreferenceCategory(this, getString(R.string.pa_title_language)))
        addPreference(PreferenceBuilder.getInstance().createListPreference(this, KEY_DEFAULT_LANGUAGE, getString(R.string.pa_title_default_language), "Test Summary", R.array.pref_array_language))

        //default mode
        addPreference(PreferenceBuilder.getInstance().createPreferenceCategory(this, getString(R.string.pa_title_navigation)))
        addPreference(PreferenceBuilder.getInstance().createListPreference(this, KEY_DEFAULT_MODE, getString(R.string.pa_title_default_mode), "Test Summary", R.array.pref_array_nav_modes))
    }

    companion object {
        private val BASE_PACKAGE_NAME = BuildConfig.APPLICATION_ID
        private val KEY_DEFAULT_LANGUAGE = BASE_PACKAGE_NAME + "DEFAULT_LANGUAGE"
        private val KEY_DEFAULT_MODE = BASE_PACKAGE_NAME + "DEFAULT_MODE"
    }
}