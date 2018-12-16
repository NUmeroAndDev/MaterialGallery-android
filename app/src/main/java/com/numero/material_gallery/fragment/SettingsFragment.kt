package com.numero.material_gallery.fragment

import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
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
        findPreference(KEY_VIEW_SOURCE).setOnPreferenceClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, sourceUrl.toUri()))
            true
        }
    }

    companion object {
        private const val KEY_MATERIAL_VERSION = "material_components_version"
        private const val KEY_VIEW_SOURCE = "view_source"

        private const val sourceUrl = "https://github.com/NUmeroAndDev/MaterialGallery-android"

        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}