package com.numero.material_gallery.repository

import androidx.lifecycle.LiveData
import com.numero.material_gallery.model.Theme

interface ConfigRepository {
    val changedTheme: LiveData<Int>

    val currentTheme: Int

    fun notifyChangedTheme()

    fun getCurrentTheme(): Theme

    fun updateAndApplyTheme(theme: Theme)
}