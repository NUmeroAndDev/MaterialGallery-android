package com.numero.material_gallery.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.numero.material_gallery.R
import com.numero.material_gallery.model.Theme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ConfigRepositoryImpl(context: Context) : ConfigRepository {

    private val settingsPreference = PreferenceManager.getDefaultSharedPreferences(context)

    private val _changedThemeEvent = Channel<Int>()
    override val changedThemeEvent: Flow<Int> = _changedThemeEvent.receiveAsFlow()

    override val currentTheme: Int
        get() {
            val shapeTheme = settingsPreference.getShapeTheme()
            return when (shapeTheme) {
                ShapeTheme.ROUNDED -> R.style.Theme_MaterialGallery_DayNight_Rounded
                ShapeTheme.CUT -> R.style.Theme_MaterialGallery_DayNight_Cut
            }
        }

    override fun notifyChangedTheme() {
        _changedThemeEvent.trySend(currentTheme)
    }

    override fun getCurrentTheme(): Theme {
        val value = settingsPreference.getString(KEY_THEME, null) ?: return Theme.SYSTEM_DEFAULT
        return Theme.find(value)
    }

    override fun updateAndApplyTheme(theme: Theme) {
        settingsPreference.edit {
            putString(KEY_THEME, theme.value)
        }
        theme.apply()
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