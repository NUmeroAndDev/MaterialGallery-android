package com.numero.material_gallery.extension

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes

fun Context.getAttrColor(@AttrRes attr: Int): Int = with(TypedValue()) {
    theme.resolveAttribute(attr, this, true)
    this.data
}