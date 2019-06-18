package com.numero.material_gallery.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.numero.material_gallery.R
import com.numero.material_gallery.model.Theme

class ConfigRepository(context: Context) : IConfigRepository {

    private val settingsPreference = PreferenceManager.getDefaultSharedPreferences(context)

    override val themeRes: Int
        get() {
            val shapeTheme = settingsPreference.getShapeTheme()
            return when (shapeTheme) {
                ShapeTheme.ROUNDED -> R.style.AppTheme_Rounded
                ShapeTheme.CUT -> R.style.AppTheme_Cut
            }
        }

    override val theme: Theme
        get() {
            val value = settingsPreference.getString(KEY_THEME, null) ?: return Theme.SYSTEM_DEFAULT
            return Theme.find(value)
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
        private const val KEY_THEME = "select_theme"
        private const val KEY_SHAPE_THEME = "shape_theme"
    }
}