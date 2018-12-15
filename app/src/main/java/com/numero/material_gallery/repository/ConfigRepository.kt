package com.numero.material_gallery.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.numero.material_gallery.R

class ConfigRepository(context: Context) : IConfigRepository {

    private val settingsPreference = PreferenceManager.getDefaultSharedPreferences(context)

    override val themeRes: Int
        get() {
            val isDarkTheme = settingsPreference.getBoolean(KEY_IS_DARK_THEME, false)
            return if (isDarkTheme) {
                R.style.DarkTheme
            } else {
                R.style.DefaultTheme
            }
        }

    companion object {
        private const val KEY_IS_DARK_THEME = "is_dark_theme"
    }
}