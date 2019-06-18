package com.numero.material_gallery.repository

import com.numero.material_gallery.model.Theme

interface IConfigRepository {
    val themeRes: Int

    val theme: Theme
}