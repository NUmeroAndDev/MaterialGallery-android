package com.numero.material_gallery.core.repository

import com.numero.material_gallery.core.ShapeTheme
import com.numero.material_gallery.core.Theme
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    val changedThemeEvent: Flow<ShapeTheme>

    val currentShapeTheme: ShapeTheme

    fun notifyChangedTheme()

    fun getCurrentTheme(): Theme

    fun updateAndApplyTheme(theme: Theme)
}