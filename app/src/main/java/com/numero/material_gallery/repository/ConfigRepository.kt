package com.numero.material_gallery.repository

import com.numero.material_gallery.model.Theme

interface ConfigRepository {
    val themeRes: Int

    fun getCurrentTheme(): Theme

    fun updateAndApplyTheme(theme: Theme)
}