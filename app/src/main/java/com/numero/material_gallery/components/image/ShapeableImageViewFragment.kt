package com.numero.material_gallery.components.image

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import kotlinx.android.synthetic.main.fragment_component_list.*
import kotlinx.android.synthetic.main.fragment_shapeable_image_view.*
import kotlinx.android.synthetic.main.fragment_shapeable_image_view.toolbar

class ShapeableImageViewFragment : MaterialContainerTransformFragment(R.layout.fragment_shapeable_image_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.menu_common)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_current_theme -> {
                        findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                        true
                    }
                    else -> false
                }
            }
        }

        scrollView.applySystemWindowInsetsPadding(applyBottom = true)
    }
}