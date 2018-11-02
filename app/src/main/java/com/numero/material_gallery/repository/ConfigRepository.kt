package com.numero.material_gallery.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.numero.material_gallery.R

class ConfigRepository(context: Context) : IConfigRepository {

    private val settingsPreference = PreferenceManager.getDefaultSharedPreferences(context)

    override val themeRes: Int
        get() {
            val isDebugMode = settingsPreference.getBoolean(KEY_IS_DEBUG_MODE, false)
            return if (isDebugMode) {
                R.style.DebugTheme
            } else {
                R.style.DefaultTheme
            }
        }

    companion object {
        private const val KEY_IS_DEBUG_MODE = "is_debug_mode"
    }
}