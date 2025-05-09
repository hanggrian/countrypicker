package com.example.dynamic

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.R
import com.google.android.material.snackbar.Snackbar
import com.hanggrian.countrypicker.CountryPickerDialog
import com.hanggrian.countrypicker.FlagDisplay
import com.hanggrian.countrypicker.NameDisplay
import com.hanggrian.countrypicker.bottomsheet.CountryPickerSheetDialog

class DynamicFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_dynamic)
        val preferences = preferenceScreen.sharedPreferences!!
        val flagDisplayPreference = findPreference<ListPreference>(PREFERENCE_FLAG_DISPLAY)!!
        val nameDisplayPreference = findPreference<ListPreference>(PREFERENCE_NAME_DISPLAY)!!
        val showDialogPreference = findPreference<Preference>(PREFERENCE_SHOW_DIALOG)!!
        val showSheetPreference = findPreference<Preference>(PREFERENCE_SHOW_SHEET)!!

        flagDisplayPreference.summary = preferences.flagDisplay.toString()
        flagDisplayPreference.setOnPreferenceChangeListener { preference, newValue ->
            preference.summary = newValue.toString().flagDisplay.toString()
            true
        }

        nameDisplayPreference.summary = preferences.nameDisplay.toString()
        nameDisplayPreference.setOnPreferenceChangeListener { preference, newValue ->
            preference.summary = newValue.toString().nameDisplay.toString()
            true
        }

        showDialogPreference.setOnPreferenceClickListener {
            val context = requireContext()
            CountryPickerDialog
                .Builder(context)
                .setFlagDisplay(preferences.flagDisplay)
                .setNameDisplay(preferences.nameDisplay)
                .setOnSelectedListener {
                    Snackbar.make(requireView(), it.getName(context), Snackbar.LENGTH_SHORT).show()
                }.show()
            false
        }
        showSheetPreference.setOnPreferenceClickListener {
            val context = requireContext()
            CountryPickerSheetDialog
                .Builder(context)
                .setFlagDisplay(preferences.flagDisplay)
                .setNameDisplay(preferences.nameDisplay)
                .setOnSelectedListener {
                    Snackbar.make(requireView(), it.getName(context), Snackbar.LENGTH_SHORT).show()
                }.show()
            false
        }
    }

    private val String.flagDisplay: FlagDisplay
        get() =
            when (this) {
                "2" -> FlagDisplay.CUSTOM
                "3" -> FlagDisplay.NONE
                else -> FlagDisplay.DEFAULT
            }

    private val String.nameDisplay: NameDisplay
        get() =
            when (this) {
                "2" -> NameDisplay.DIAL_CODE
                "3" -> NameDisplay.ISO_CODE
                else -> NameDisplay.DEFAULT
            }

    private inline val SharedPreferences.flagDisplay: FlagDisplay
        get() = getString(PREFERENCE_FLAG_DISPLAY, "")!!.flagDisplay

    private inline val SharedPreferences.nameDisplay: NameDisplay
        get() = getString(PREFERENCE_NAME_DISPLAY, "")!!.nameDisplay
}
