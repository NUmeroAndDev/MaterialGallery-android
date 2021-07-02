package com.numero.material_gallery.components.snackbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentSnackbarBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class SnackbarFragment : MaterialContainerTransformFragment(R.layout.fragment_snackbar) {

    private val binding by viewBinding { FragmentSnackbarBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_common, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_current_theme -> {
                findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                true
            }
            else -> false
        }
    }
}