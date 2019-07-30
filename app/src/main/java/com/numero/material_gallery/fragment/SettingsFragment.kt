package com.numero.material_gallery.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import com.numero.material_gallery.BuildConfig
import com.numero.material_gallery.R
import com.numero.material_gallery.model.Theme

class SettingsFragment : PreferenceFragmentCompat() {

    private var listener: SettingsFragmentListener? = null
    private lateinit var appVersionPreference: Preference

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SettingsFragmentListener) {
            listener = context
        }
    }

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
        appVersionPreference = findPreference<Preference>(KEY_APP_VERSION)!!.apply {
            summary = BuildConfig.VERSION_NAME
        }
    }

    override fun onResume() {
        super.onResume()
        checkUpdate()
    }

    private fun checkUpdate() {
        AppUpdateManagerFactory.create(requireActivity()).appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                appVersionPreference.apply {
                    setIcon(R.drawable.ic_attention)
                    setTitle(R.string.update_message)
                    summary = ""
                    setOnPreferenceClickListener {
                        listener?.doUpdate(appUpdateInfo)
                        true
                    }
                }
            }
        }
    }

    interface SettingsFragmentListener {
        fun doUpdate(appUpdateInfo: AppUpdateInfo)
    }

    companion object {
        private const val KEY_SELECT_THEME = "select_theme"
        private const val KEY_MATERIAL_VERSION = "material_components_version"
        private const val KEY_VIEW_SOURCE = "view_source"
        private const val KEY_LICENSES = "licenses"
        private const val KEY_APP_VERSION = "app_version"

        private const val sourceUrl = "https://github.com/NUmeroAndDev/MaterialGallery-android"

        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}