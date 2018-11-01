package com.numero.material_gallery.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.numero.material_gallery.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}