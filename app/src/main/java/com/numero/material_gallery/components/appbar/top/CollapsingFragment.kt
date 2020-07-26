package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_collapsing.*

class CollapsingFragment : MaterialContainerTransformFragment(R.layout.fragment_collapsing) {

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
    }
}