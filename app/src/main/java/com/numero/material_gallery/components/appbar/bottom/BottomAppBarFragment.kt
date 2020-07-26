package com.numero.material_gallery.components.appbar.bottom

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import kotlinx.android.synthetic.main.fragment_bottom_app_bar.*

class BottomAppBarFragment : MaterialContainerTransformFragment(R.layout.fragment_bottom_app_bar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked FAB", Toast.LENGTH_SHORT).show()
        }

        fabPositionRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.attachedCenterRadioButton -> bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                R.id.attachedEndRadioButton -> bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            }
        }

        fabAnimationRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.scaleRadioButton -> bottomAppBar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SCALE
                R.id.slideRadioButton -> bottomAppBar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE
            }
        }

        fabVisiblyRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.showRadioButton -> fab.show()
                R.id.hideRadioButton -> fab.hide()
            }
        }

        hideOnScrollSwitch.setOnCheckedChangeListener { _, isChecked ->
            bottomAppBar.hideOnScroll = isChecked
            if (isChecked.not()) {
                bottomAppBar.performShow()
            }
        }

        bottomAppBar.replaceMenu(R.menu.bottom_app_bar)

        scrollView.applySystemWindowInsetsPadding(applyBottom = true)
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