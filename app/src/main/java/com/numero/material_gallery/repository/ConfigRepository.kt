package com.numero.material_gallery.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.numero.material_gallery.R

class ConfigRepository(context: Context) : IConfigRepository {

    private val settingsPreference = PreferenceManager.getDefaultSharedPreferences(context)

    override val themeRes: Int
        get() {
            val isDarkTheme = settingsPreference.getBoolean(KEY_IS_DARK_THEME, false)
            val shapeTheme = settingsPreference.getShapeTheme()
            return when (shapeTheme) {
                ShapeTheme.ROUNDED -> {
                    if (isDarkTheme) {
                        R.style.DarkTheme_Rounded
                    } else {
                        R.style.LightTheme_Rounded
                    }
                }
                ShapeTheme.CUT -> {
                    if (isDarkTheme) {
                        R.style.DarkTheme_Cut
                    } else {
                        R.style.LightTheme_Cut
                    }
                }
            }
        }

    private fun SharedPreferences.getShapeTheme(): ShapeTheme {
        val value = getString(KEY_SHAPE_THEME, null) ?: return ShapeTheme.ROUNDED
        return ShapeTheme.findShapeTheme(value)
    }

    private enum class ShapeTheme(private val value: String) {
        ROUNDED("rounded"),
        CUT("cut");

        companion object {
            fun findShapeTheme(value: String): ShapeTheme {
                return checkNotNull(values().find { it.value == value })
            }
        }
    }

    companion object {
        private const val KEY_IS_DARK_THEME = "is_dark_theme"
        private const val KEY_SHAPE_THEME = "shape_theme"
    }
}