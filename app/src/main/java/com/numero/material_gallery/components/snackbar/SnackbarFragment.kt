package com.numero.material_gallery.components.snackbar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_snackbar.*

class SnackbarFragment : MaterialContainerTransformFragment(R.layout.fragment_snackbar) {

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

        fab.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked FAB", Toast.LENGTH_SHORT).show()
        }

        showSnackbarButton.setOnClickListener {
            Snackbar.make(it, "Message", Snackbar.LENGTH_SHORT).setAnchorView(fab).show()
        }
        showActionSnackbarButton.setOnClickListener {
            Snackbar.make(it, "Message", Snackbar.LENGTH_LONG).setAction("Action") {
                Toast.makeText(requireContext(), "Clicked Snackbar action", Toast.LENGTH_SHORT).show()
            }.setAnchorView(fab).show()
        }
    }
}