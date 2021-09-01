package com.numero.material_gallery.components.tab

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentTabBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding

class TabFragment : MaterialContainerTransformFragment(R.layout.fragment_tab) {

    private val binding by viewBinding { FragmentTabBinding.bind(it) }

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
        binding.withBadgeTabLayout.getTabAt(0)?.apply {
            orCreateBadge
        }
        binding.withBadgeTabLayout.getTabAt(1)?.apply {
            orCreateBadge.apply {
                number = 10
            }
        }
        binding.withBadgeTabLayout.getTabAt(2)?.apply {
            orCreateBadge.apply {
                number = 1000
            }
        }
        binding.withBadgeTabLayout.getTabAt(3)?.apply {
            orCreateBadge.apply {
                maxCharacterCount = 5
                number = 5000
            }
        }
    }
}