package com.hendraanggrian.appcompat.countrypicker.demo

import android.os.Bundle
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.preference.PreferenceFragmentCompat
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog
import com.hendraanggrian.appcompat.countrypicker.CountryPickerSheetDialog

class DemoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_demo)
        val preferences = preferenceScreen.sharedPreferences
        findPreference("show_dialog").setOnPreferenceClickListener {
            CountryPickerDialog.Builder(context!!)
                .setShowFlag(preferences.getBoolean(PREFERENCE_IS_SHOW_FLAG, true))
                .setOnSelectedListener {
                    makeText(context, it.getName(context!!), LENGTH_LONG).show()
                }
                .show()
            false
        }
        findPreference("show_sheet").setOnPreferenceClickListener {
            CountryPickerSheetDialog(context!!)
                .apply {
                    picker.setShowFlag(preferences.getBoolean(PREFERENCE_IS_SHOW_FLAG, true))
                    setOnSelectedListener {
                        makeText(context, it.getName(context!!), LENGTH_LONG).show()
                    }
                }
                .show()
            false
        }
    }
}