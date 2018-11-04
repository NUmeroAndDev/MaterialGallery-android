package com.numero.material_gallery.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Context.getAttrColor(@AttrRes attr: Int): Int = with(TypedValue()) {
    theme.resolveAttribute(attr, this, true)
    this.data
}

fun Context.getTintedDrawable(@DrawableRes drawableRes: Int, @ColorInt color: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)?.apply {
        DrawableCompat.setTint(this, color)
    }
}