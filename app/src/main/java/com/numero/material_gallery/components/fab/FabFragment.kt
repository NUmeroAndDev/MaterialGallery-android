package com.numero.material_gallery.components.fab

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_fab.*

class FabFragment : MaterialContainerTransformFragment(R.layout.fragment_fab) {

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

        fabSizeRadioGroup.setOnCheckedChangeListener { _, id ->
            fab.size = when (id) {
                R.id.fabSizeMinRadioButton -> FloatingActionButton.SIZE_MINI
                else -> FloatingActionButton.SIZE_NORMAL
            }
        }

        fab.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked FAB", Toast.LENGTH_SHORT).show()
        }
    }
}