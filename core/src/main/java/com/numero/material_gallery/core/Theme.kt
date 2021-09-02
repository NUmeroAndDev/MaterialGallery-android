package com.numero.material_gallery.core

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.numero.material_gallery.core.repository.ConfigRepositoryImpl

enum class Theme(val value: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM_DEFAULT("system_default");

    fun apply() {
        val mode = when (this) {
            LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            DARK -> AppCompatDelegate.MODE_NIGHT_YES
            SYSTEM_DEFAULT -> if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {// FIXME support Q
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            } else {
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    companion object {
        fun find(value: String): Theme {
            return checkNotNull(values().find { it.value == value })
        }
    }
}

enum class ShapeTheme(private val value: String) {
    ROUNDED("rounded"),
    CUT("cut");

    companion object {
        fun findShapeTheme(value: String): ShapeTheme {
            return checkNotNull(ShapeTheme.values().find { it.value == value })
        }
    }
}