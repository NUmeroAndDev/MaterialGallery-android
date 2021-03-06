package com.numero.material_gallery.components.fab

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentExtendedFabBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class ExtendedFabFragment : MaterialContainerTransformFragment(R.layout.fragment_extended_fab) {

    private val binding by viewBinding { FragmentExtendedFabBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabStyleRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabStyleExtendRadioButton -> binding.extendedFab.extend()
                else -> binding.extendedFab.shrink()
            }
        }

        binding.fabVisibilityRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabShowRadioButton -> binding.extendedFab.show()
                else -> binding.extendedFab.hide()
            }
        }

        binding.extendedFab.setOnClickListener {
            Snackbar.make(
                binding.rootLayout,
                "Clicked FAB",
                Snackbar.LENGTH_SHORT
            ).setAnchorView(it).show()
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