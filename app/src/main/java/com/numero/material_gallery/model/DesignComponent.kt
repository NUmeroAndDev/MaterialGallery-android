package com.numero.material_gallery.model

import androidx.annotation.StringRes
import com.numero.material_gallery.R

enum class DesignComponent(@StringRes val nameRes: Int, val isEnable: Boolean) {

    BACKDROP(
            R.string.backdrop,
            false
    ),

    BOTTOM_APP_BAR(
            R.string.bottom_app_bar,
            true
    ),

    BOTTOM_NAVIGATION(
            R.string.bottom_navigation,
            true
    ),

    BOTTOM_SHEET_MODAL(
            R.string.bottom_sheet_modal,
            true
    ),

    BOTTOM_SHEET_PERSISTENT(
            R.string.bottom_sheet_persistent,
            true
    ),

    MATERIAL_BUTTON(
            R.string.material_button,
            true
    ),

    FAB(
            R.string.fab,
            true
    ),

    CARD(
            R.string.card,
            true
    ),

    CHIPS(
            R.string.chips,
            true
    ),

    NAVIGATION_DRAWER(
            R.string.navigation_drawer,
            true
    ),

    SNACKBAR(
            R.string.snachbar,
            true
    ),

    TAB(
            R.string.tab,
            true
    ),

    TEXT_FIELDS(
            R.string.text_field,
            true
    )
}