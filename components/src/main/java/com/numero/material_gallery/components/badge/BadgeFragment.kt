package com.numero.material_gallery.components.badge

import android.os.Bundle
import android.view.View
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentBadgeBinding
import com.numero.material_gallery.core.delegate.viewBinding

class BadgeFragment : ComponentFragment(R.layout.fragment_badge) {

    private val binding by viewBinding { FragmentBadgeBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBadgeWithTab()
        setupBadgeWithNavigationBar()
        setupBadgeWithTopAppBar()
    }

    private fun setupBadgeWithTab() {
        binding.tabLayout.getTabAt(0)?.apply {
            orCreateBadge
        }
        binding.tabLayout.getTabAt(1)?.apply {
            orCreateBadge.apply {
                number = 10
            }
        }
        binding.tabLayout.getTabAt(2)?.apply {
            orCreateBadge.apply {
                number = 1000
            }
        }
        binding.tabLayout.getTabAt(3)?.apply {
            orCreateBadge.apply {
                maxCharacterCount = 5
                number = 5000
            }
        }
    }

    private fun setupBadgeWithNavigationBar() {
        binding.navigationBar.apply {
            getOrCreateBadge(R.id.navigation_item_1)
            getOrCreateBadge(R.id.navigation_item_2).apply {
                number = 10
            }
            getOrCreateBadge(R.id.navigation_item_3).apply {
                number = 1000
            }
            menu.findItem(R.id.navigation_item_4).apply {
                isVisible = true
            }
            getOrCreateBadge(R.id.navigation_item_4).apply {
                maxCharacterCount = 5
                number = 5000
            }
        }
    }

    @androidx.annotation.OptIn(ExperimentalBadgeUtils::class)
    private fun setupBadgeWithTopAppBar() {
        binding.topAppBar.inflateMenu(R.menu.menu_action_bar)

        val badge = BadgeDrawable.create(requireContext())
        BadgeUtils.attachBadgeDrawable(badge, binding.topAppBar, R.id.action_info)
    }
}