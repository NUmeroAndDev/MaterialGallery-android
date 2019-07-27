package com.numero.material_gallery.repository

import com.numero.material_gallery.model.Theme

interface IConfigRepository {
    val themeRes: Int

    fun getCurrentTheme(): Theme

    fun updateAndApplyTheme(theme: Theme)
}