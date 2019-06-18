package com.numero.material_gallery.fragment

import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.numero.material_gallery.BuildConfig
import com.numero.material_gallery.R
import com.numero.material_gallery.model.Theme

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPreference<ListPreference>(KEY_SELECT_THEME)?.setOnPreferenceChangeListener { preference, newValue ->
            if (newValue is String) {
                Theme.find(newValue).apply()
            }
            true
        }
        findPreference<Preference>(KEY_MATERIAL_VERSION)?.summary = BuildConfig.MATERIAL_COMPONENTS_VERSION
        findPreference<Preference>(KEY_VIEW_SOURCE)?.setOnPreferenceClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, sourceUrl.toUri()))
            true
        }
        findPreference<Preference>(KEY_LICENSES)?.setOnPreferenceClickListener {
            startActivity(Intent(context, OssLicensesMenuActivity::class.java))
            true
        }
    }

    companion object {
        private const val KEY_SELECT_THEME = "select_theme"
        private const val KEY_MATERIAL_VERSION = "material_components_version"
        private const val KEY_VIEW_SOURCE = "view_source"
        private const val KEY_LICENSES = "licenses"

        private const val sourceUrl = "https://github.com/NUmeroAndDev/MaterialGallery-android"

        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}