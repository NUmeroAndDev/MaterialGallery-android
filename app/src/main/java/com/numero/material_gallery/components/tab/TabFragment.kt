package com.numero.material_gallery.components.tab

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.databinding.FragmentTabBinding

class TabFragment : MaterialContainerTransformFragment(R.layout.fragment_tab) {

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTabBinding.bind(view)
        setupTab()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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