package com.numero.material_gallery.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.numero.material_gallery.BuildConfig
import com.numero.material_gallery.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPreference(KEY_MATERIAL_VERSION).summary = BuildConfig.MATERIAL_COMPONENTS_VERSION
    }

    companion object {
        private const val KEY_MATERIAL_VERSION = "material_components_version"

        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}