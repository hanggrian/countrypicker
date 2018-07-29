package com.hendraanggrian.appcompat.countrypicker.demo

import android.os.Bundle
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.preference.PreferenceFragmentCompat
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog
import com.hendraanggrian.appcompat.countrypicker.CountryPickerSheetDialogFragment

class DemoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_demo)
        findPreference("show_dialog").setOnPreferenceClickListener {
            val preferences = preferenceScreen.sharedPreferences
            CountryPickerDialog.Builder(context!!)
                .setShowFlag(preferences.getBoolean(PREFERENCE_IS_SHOW_FLAG, true))
                .setOnSelectedListener {
                    makeText(context, it.getName(context!!), LENGTH_LONG).show()
                }
                .show()
            false
        }
        findPreference("show_sheet").setOnPreferenceClickListener {
            SheetFragment().show(childFragmentManager, null)
            false
        }
    }

    class SheetFragment : CountryPickerSheetDialogFragment()
}