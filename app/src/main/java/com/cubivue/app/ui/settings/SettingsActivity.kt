package com.cubivue.app.ui.settings

import android.support.v14.preference.SwitchPreference
import android.support.v7.preference.PreferenceCategory
import android.support.v7.preference.PreferenceFragmentCompat
import com.cubivue.base.util.preference.preferenceActivity.activity.BaseSettingsActivity
import com.cubivue.base.util.preference.preferenceActivity.fragment.NewPreferenceHeaderFragment

class SettingsActivity : BaseSettingsActivity() {


    override fun onCreateNavigation(fragment: PreferenceFragmentCompat) {
        super.onCreateNavigation(fragment)
        addNavigationPreference()
    }

    /**
     * Dynamically adds a new navigation preference to the activity.
     */
    override fun addNavigationPreference() {
        super.addNavigationPreference()

        //adding category
        val preferenceCategory = PreferenceCategory(this)
        preferenceCategory.title = "Info Message"

        //adding preference
        val infoMessagePref = SwitchPreference(this)
        infoMessagePref.title = getPreferenceHeaderTitle()
        infoMessagePref.key = "info"
        infoMessagePref.fragment = NewPreferenceHeaderFragment::class.java.name
        infoMessagePref.summary = "Test Summary"

        val warningMessagePref = SwitchPreference(this)
        warningMessagePref.title = getPreferenceHeaderTitle()
        warningMessagePref.key = "warning"
        warningMessagePref.fragment = NewPreferenceHeaderFragment::class.java.name
        warningMessagePref.summary = "Test Summary"

        //adding pref to screen
        addPreference(preferenceCategory)
        addPreference(infoMessagePref)
        addPreference(warningMessagePref)
    }
}