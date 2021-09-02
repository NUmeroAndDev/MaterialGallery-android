package com.numero.material_gallery.components.snackbar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentSnackbarBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class SnackbarFragment : ComponentFragment(R.layout.fragment_snackbar) {

    private val binding by viewBinding { FragmentSnackbarBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked FAB", Toast.LENGTH_SHORT).show()
        }

        binding.showSnackbarButton.setOnClickListener {
            Snackbar.make(it, "Message", Snackbar.LENGTH_SHORT).setAnchorView(binding.fab).show()
        }
        binding.showActionSnackbarButton.setOnClickListener {
            Snackbar.make(it, "Message", Snackbar.LENGTH_LONG).setAction("Action") {
                Toast.makeText(requireContext(), "Clicked Snackbar action", Toast.LENGTH_SHORT)
                    .show()
            }.setAnchorView(binding.fab).show()
        }

        binding.rootLayout.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}