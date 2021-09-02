package com.numero.material_gallery.core.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.numero.material_gallery.core.ShapeTheme
import com.numero.material_gallery.core.Theme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ConfigRepositoryImpl(context: Context) : ConfigRepository {

    private val settingsPreference = PreferenceManager.getDefaultSharedPreferences(context)

    private val _changedThemeEvent = Channel<ShapeTheme>()
    override val changedThemeEvent: Flow<ShapeTheme> = _changedThemeEvent.receiveAsFlow()

    override val currentShapeTheme: ShapeTheme
        get() = settingsPreference.getShapeTheme()


    override fun notifyChangedTheme() {
        _changedThemeEvent.trySend(currentShapeTheme)
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

    companion object {
        private const val KEY_THEME = "select_theme"
        private const val KEY_SHAPE_THEME = "shape_theme"
    }
}