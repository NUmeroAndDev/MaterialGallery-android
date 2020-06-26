package com.numero.material_gallery.components.appbar.top

import androidx.annotation.StringRes
import com.numero.material_gallery.R
import com.numero.material_gallery.model.ListItem

enum class TopAppBarType(
        @StringRes override val titleRes: Int
) : ListItem {
    ACTION_BAR(
            R.string.actionbar
    ),
    TOOLBAR(
            R.string.toolbar
    ),
    LIFT_ON_SCROLL(
            R.string.lift_on_scroll
    ),
    COLLAPSING(
            R.string.collapsing
    )
}