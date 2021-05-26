package com.numero.material_gallery.repository

import com.numero.material_gallery.model.Theme
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    val changedThemeEvent: Flow<Int>

    val currentTheme: Int

    fun notifyChangedTheme()

    fun getCurrentTheme(): Theme

    fun updateAndApplyTheme(theme: Theme)
}