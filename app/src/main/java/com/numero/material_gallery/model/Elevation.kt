package com.numero.material_gallery.model

import androidx.annotation.DimenRes
import com.numero.material_gallery.R

enum class Elevation(@DimenRes val dimenRes: Int) {
    SHADOW_0DP(R.dimen.elevation_0dp),
    SHADOW_2DP(R.dimen.elevation_2dp),
    SHADOW_4DP(R.dimen.elevation_4dp),
    SHADOW_8DP(R.dimen.elevation_8dp)
}