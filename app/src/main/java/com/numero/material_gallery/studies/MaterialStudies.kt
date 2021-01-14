package com.numero.material_gallery.studies

import androidx.annotation.StringRes
import com.numero.material_gallery.R

enum class MaterialStudies(
    val iconRes: Int,
    @StringRes val titleRes: Int
) {
    Reply(
        R.mipmap.ic_reply_launcher,
        R.string.label_reply
    ),
    Shrine(
        R.mipmap.ic_shrine_launcher,
        R.string.label_shrine
    )
}