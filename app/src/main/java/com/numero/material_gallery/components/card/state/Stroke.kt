package com.numero.material_gallery.components.card.state

import androidx.annotation.DimenRes
import com.numero.material_gallery.R

enum class Stroke(@DimenRes val dimenRes: Int) {
    STROKE_0DP(R.dimen.stroke_0dp),
    STROKE_1DP(R.dimen.stroke_1dp),
    STROKE_2DP(R.dimen.stroke_2dp),
    STROKE_3DP(R.dimen.stroke_3dp)
}