package com.numero.material_gallery.components.fab

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentFabBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class FabFragment : MaterialContainerTransformFragment(R.layout.fragment_fab) {

    private val binding by viewBinding { FragmentFabBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabs = listOf(
            binding.primaryFab,
            binding.secondaryFab,
            binding.surfaceFab,
        )
        binding.fabSizeRadioGroup.setOnCheckedChangeListener { _, id ->
            fabs.forEach {
                it.size = when (id) {
                    R.id.fabSizeMinRadioButton -> FloatingActionButton.SIZE_MINI
                    else -> FloatingActionButton.SIZE_NORMAL
                }
            }
        }
        binding.fabVisibilityRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabShowRadioButton -> fabs.forEach { it.show() }
                else -> fabs.forEach { it.hide() }
            }
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