package com.numero.material_gallery.components.tab

import android.os.Bundle
import android.view.View
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentTabBinding
import com.numero.material_gallery.core.delegate.viewBinding

class TabFragment : ComponentFragment(R.layout.fragment_tab) {

    private val binding by viewBinding { FragmentTabBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTab()
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