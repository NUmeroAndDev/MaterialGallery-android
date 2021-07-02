package com.numero.material_gallery.components.navigationrail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigationrail.NavigationRailView
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentNavigationRailBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class NavigationRailFragment :
    MaterialContainerTransformFragment(R.layout.fragment_navigation_rail) {

    private val binding by viewBinding { FragmentNavigationRailBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.labelVisibilityRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.navigationRailView.labelVisibilityMode = when (checkedId) {
                R.id.labelVisibilityAutoRadioButton -> NavigationRailView.LABEL_VISIBILITY_AUTO
                R.id.labelVisibilitySelectedRadioButton -> NavigationRailView.LABEL_VISIBILITY_SELECTED
                R.id.labelVisibilityLabeledRadioButton -> NavigationRailView.LABEL_VISIBILITY_LABELED
                R.id.labelVisibilityUnlabeledRadioButton -> NavigationRailView.LABEL_VISIBILITY_UNLABELED
                else -> throw Exception("Not defined id")
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