package com.numero.material_gallery.components.card.state

import androidx.annotation.DimenRes
import com.numero.material_gallery.R

enum class Corner(@DimenRes val dimenRes: Int) {
    CORNER_0DP(R.dimen.round_0dp),
    CORNER_2DP(R.dimen.round_2dp),
    CORNER_4DP(R.dimen.round_4dp),
    CORNER_8DP(R.dimen.round_8dp),
    CORNER_16DP(R.dimen.round_16dp)
}