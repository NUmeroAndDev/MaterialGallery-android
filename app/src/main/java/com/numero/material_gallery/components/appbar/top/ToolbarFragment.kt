package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_toolbar.*

class ToolbarFragment : MaterialContainerTransformFragment(R.layout.fragment_toolbar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
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

    private fun initViews() {
        val toolbarList = listOf(primaryToolbar, surfaceToolbar, primarySurfaceToolbar)
        toolbarList.forEach {
            it.inflateMenu(R.menu.menu_action_bar)
        }
    }
}