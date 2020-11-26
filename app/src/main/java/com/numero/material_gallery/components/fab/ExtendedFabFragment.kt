package com.numero.material_gallery.components.fab

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import kotlinx.android.synthetic.main.fragment_extended_fab.*

class ExtendedFabFragment : MaterialContainerTransformFragment(R.layout.fragment_extended_fab) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabStyleRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabStyleExtendRadioButton -> extendedFab.extend()
                else -> extendedFab.shrink()
            }
        }

        fabVisibilityRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabShowRadioButton -> extendedFab.show()
                else -> extendedFab.hide()
            }
        }

        extendedFab.setOnClickListener {
            Snackbar.make(rootLayout, "Clicked FAB", Snackbar.LENGTH_SHORT).setAnchorView(it).show()
        }

        rootLayout.applySystemWindowInsetsPadding(applyBottom = true)
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