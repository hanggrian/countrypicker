package com.hendraanggrian.appcompat.countrydialog.demo

import android.os.Bundle
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.preference.PreferenceFragmentCompat
import com.hendraanggrian.appcompat.countrydialog.CountryDialog

class DemoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_demo)
        findPreference("show").setOnPreferenceClickListener {
            val preferences = preferenceScreen.sharedPreferences
            CountryDialog.Builder(context!!)
                .setShowFlag(preferences.getBoolean(PREFERENCE_SHOW_FLAG, true))
                .setShowDialCode(preferences.getBoolean(PREFERENCE_SHOW_DIAL_CODE, false))
                .setOnSelectedListener {
                    makeText(context, it.getName(context!!), LENGTH_LONG).show()
                }
                .show()
            false
        }
    }
}