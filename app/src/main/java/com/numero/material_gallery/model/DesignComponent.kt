package com.numero.material_gallery.model

import androidx.annotation.StringRes
import com.numero.material_gallery.R

enum class DesignComponent(
        @StringRes override val titleRes: Int,
        val isEnable: Boolean
) : IListItem {

    BACKDROP(
            R.string.backdrop,
            false
    ),

    BOTTOM_NAVIGATION(
            R.string.bottom_navigation,
            true
    ),

    BOTTOM_APP_BAR(
            R.string.bottom_app_bar,
            true
    ),

    BOTTOM_SHEET(
            R.string.bottom_sheet,
            true
    ),

    CHIPS(
            R.string.chips,
            false
    ),

    FAB(
            R.string.fab,
            true
    ),

    MATERIAL_BUTTON(
            R.string.material_button,
            true
    ),

    MATERIAL_CARD(
            R.string.material_card,
            true
    ),

    MODAL_BOTTOM_SHEET(
            R.string.modal_bottom_sheet,
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
            false
    ),

    TEXT_FIELDS(
            R.string.text_field,
            true
    ),

    TOP_APP_BAR(
            R.string.top_app_bar,
            true
    )
}