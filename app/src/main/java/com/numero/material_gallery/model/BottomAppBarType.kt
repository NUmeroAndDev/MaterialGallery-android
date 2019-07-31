package com.numero.material_gallery.model

import androidx.annotation.StringRes
import com.numero.material_gallery.R

enum class BottomAppBarType(
        @StringRes override val titleRes: Int
):ListItem {
    BOTTOM_APP_BAR(
            R.string.bottom_app_bar
    ),
    HIDE_ON_SCROLL(
            R.string.hide_on_scroll
    )
}