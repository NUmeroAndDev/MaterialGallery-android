package com.numero.material_gallery.components.tab

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment : MaterialContainerTransformFragment(R.layout.fragment_tab) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTab()
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

    private fun setupTab() {
        withBadgeTabLayout.getTabAt(0)?.apply {
            orCreateBadge
        }
        withBadgeTabLayout.getTabAt(1)?.apply {
            orCreateBadge.apply {
                number = 10
            }
        }
        withBadgeTabLayout.getTabAt(2)?.apply {
            orCreateBadge.apply {
                number = 1000
            }
        }
        withBadgeTabLayout.getTabAt(3)?.apply {
            orCreateBadge.apply {
                maxCharacterCount = 5
                number = 5000
            }
        }
    }
}