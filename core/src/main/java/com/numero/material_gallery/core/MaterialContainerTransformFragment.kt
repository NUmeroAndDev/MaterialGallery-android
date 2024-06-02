package com.numero.material_gallery.core

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialContainerTransform

abstract class MaterialContainerTransformFragment : Fragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            val backgroundColor = MaterialColors.getColor(
                    requireContext(),
                    android.R.attr.colorBackground,
                    "The attribute is not set in the current theme"
            )
            setAllContainerColors(backgroundColor)
            scrimColor = Color.TRANSPARENT
        }
        exitTransition = Hold()
        reenterTransition = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }
}