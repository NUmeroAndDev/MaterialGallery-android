package com.numero.material_gallery.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import com.numero.material_gallery.BuildConfig
import com.numero.material_gallery.R
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import com.numero.material_gallery.core.launchWhenStartedIn
import com.numero.material_gallery.model.Theme
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var appVersionPreference: Preference
    private val configRepository by inject<ConfigRepository>()
    private val appUpdateManager by inject<AppUpdateManager>()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialFadeThrough()
        enterTransition = MaterialFadeThrough()

        findPreference<ListPreference>(KEY_SELECT_THEME)?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue is String) {
                Theme.find(newValue).apply()
            }
            true
        }
        findPreference<ListPreference>(KEY_SELECT_SHAPE)?.setOnPreferenceChangeListener { _, _ ->
            configRepository.notifyChangedTheme()
            true
        }
        findPreference<Preference>(KEY_MATERIAL_VERSION)?.summary =
            BuildConfig.MATERIAL_COMPONENTS_VERSION
        findPreference<Preference>(KEY_VIEW_SOURCE)?.setOnPreferenceClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, sourceUrl.toUri()))
            true
        }
        findPreference<Preference>(KEY_LICENSES)?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.action_Settings_to_Licenses)
            true
        }
        appVersionPreference = findPreference<Preference>(KEY_APP_VERSION)!!.apply {
            summary = BuildConfig.VERSION_NAME
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setBackgroundColor(MaterialColors.getColor(view, android.R.attr.colorBackground))
        view.applySystemWindowInsetsPadding(applyTop = true)
    }

    override fun onResume() {
        super.onResume()
        checkUpdate()
    }

    private fun checkUpdate() {
        appUpdateManager.requestUpdateFlow()
            .onEach { appUpdate ->
                when (appUpdate) {
                    is AppUpdateResult.Available -> {
                        appVersionPreference.apply {
                            setIcon(R.drawable.ic_attention)
                            setTitle(R.string.update_message)
                            summary = ""
                            setOnPreferenceClickListener {
                                appUpdate.startImmediateUpdate(
                                    this@SettingsFragment,
                                    UPDATE_REQUEST_CODE
                                )
                                true
                            }
                        }
                    }
                    else -> Unit
                }
            }
            .launchWhenStartedIn(viewLifecycleOwner)
    }

    companion object {
        private const val UPDATE_REQUEST_CODE = 1

        private const val KEY_SELECT_THEME = "select_theme"
        private const val KEY_SELECT_SHAPE = "shape_theme"
        private const val KEY_MATERIAL_VERSION = "material_components_version"
        private const val KEY_VIEW_SOURCE = "view_source"
        private const val KEY_LICENSES = "licenses"
        private const val KEY_APP_VERSION = "app_version"

        private const val sourceUrl = "https://github.com/NUmeroAndDev/MaterialGallery-android"

        fun newInstance(): SettingsFragment =
            SettingsFragment()
    }
}