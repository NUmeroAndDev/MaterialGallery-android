package com.numero.material_gallery.components

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialContainerTransform

open class MaterialContainerTransformFragment : Fragment {

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
        }
        exitTransition = Hold()
        reenterTransition = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }
}