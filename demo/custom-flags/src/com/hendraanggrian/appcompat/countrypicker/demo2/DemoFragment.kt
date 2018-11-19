package com.hendraanggrian.appcompat.countrypicker.demo2

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog
import com.hendraanggrian.appcompat.countrypicker.CountryPickerSheetDialog

class DemoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_demo)
        val preferences = preferenceScreen.sharedPreferences
        findPreference("show_dialog").setOnPreferenceClickListener {
            CountryPickerDialog.Builder(context!!)
                .setFlagShown(preferences.getBoolean(PREFERENCE_IS_SHOW_FLAG, true))
                .setOnSelectedListener {
                    Snackbar.make(view!!, it.getName(context!!), Snackbar.LENGTH_SHORT).show()
                }
                .show()
            false
        }
        findPreference("show_sheet").setOnPreferenceClickListener {
            CountryPickerSheetDialog(context!!)
                .apply {
                    picker.setShowFlag(preferences.getBoolean(PREFERENCE_IS_SHOW_FLAG, true))
                    setOnSelectedListener {
                        Snackbar.make(view!!, it.getName(context), Snackbar.LENGTH_SHORT).show()
                    }
                }
                .show()
            false
        }
    }
}