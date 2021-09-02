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
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import com.numero.material_gallery.BuildConfig
import com.numero.material_gallery.R
import com.numero.material_gallery.core.launchWhenStartedIn
import com.numero.material_gallery.core.Theme
import com.numero.material_gallery.core.repository.ConfigRepository
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var appVersionPreference: Preference

    @Inject
    lateinit var configRepository: ConfigRepository

    @Inject
    lateinit var appUpdateManager: AppUpdateManager

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
        view.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
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
            .catch {}
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
    }
}