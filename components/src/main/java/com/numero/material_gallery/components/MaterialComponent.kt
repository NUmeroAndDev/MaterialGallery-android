package com.numero.material_gallery.components

import androidx.annotation.StringRes

enum class MaterialComponent(
    @StringRes val titleRes: Int
) {

    BADGE(
        R.string.badge
    ),

    BOTTOM_APP_BAR(
        R.string.bottom_app_bar
    ),

    BOTTOM_SHEET(
        R.string.bottom_sheet
    ),

    Carousel(
        R.string.carousel
    ),

    CHECKBOX(
        R.string.checkbox
    ),

    CHIPS(
        R.string.chips
    ),

    DATE_PICKER(
        R.string.date_picker
    ),

    DIVIDER(
        R.string.divider
    ),

    EXTENDED_FAB(
        R.string.extended_fab
    ),

    FAB(
        R.string.fab
    ),

    IMAGE_VIEW(
        R.string.shapeable_image_view
    ),

    MATERIAL_BUTTON(
        R.string.material_button
    ),

    MATERIAL_BUTTON_TOGGLE_GROUP(
        R.string.material_button_toggle_group
    ),

    MATERIAL_CARD(
        R.string.material_card
    ),

    MATERIAL_DIALOG(
        R.string.material_dialog
    ),

    MENU(
        R.string.menu
    ),

    MODAL_BOTTOM_SHEET(
        R.string.modal_bottom_sheet
    ),

    NAVIGATION_BAR(
        R.string.bottom_navigation
    ),

    NAVIGATION_DRAWER(
        R.string.navigation_drawer
    ),

    NAVIGATION_RAIL(
        R.string.navigation_rail
    ),

    PROGRESS_INDICATOR(
        R.string.progress_indicator
    ),

    RADIO_BUTTON(
        R.string.radio_button
    ),

    SEARCH_BAR(
        R.string.search_bar
    ),

    SIDE_SHEET(
        R.string.side_sheet
    ),

    SLIDER(
        R.string.slider
    ),

    SNACKBAR(
        R.string.snackbar
    ),

    SWITCH(
        R.string.material_switch
    ),

    TAB(
        R.string.tab
    ),

    TEXT_FIELDS(
        R.string.text_field
    ),

    TIME_PICKER(
        R.string.time_picker
    ),

    TOP_APP_BAR(
        R.string.top_app_bar
    )
}