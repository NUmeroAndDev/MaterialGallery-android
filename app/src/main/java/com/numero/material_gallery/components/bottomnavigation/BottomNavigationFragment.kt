package com.numero.material_gallery.components.bottomnavigation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : MaterialContainerTransformFragment(R.layout.fragment_bottom_navigation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.menu_common)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_current_theme -> {
                        findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                        true
                    }
                    else -> false
                }
            }
        }

        initViews()
        scrollView.applySystemWindowInsetsPadding(applyBottom = true)
    }

    private fun initViews() {
        val listener = BottomNavigationView.OnNavigationItemSelectedListener {
            bottomNavigation.setCheckedItem(it.itemId, true)
            coloredBottomNavigation.setCheckedItem(it.itemId, true)
            false
        }
        bottomNavigation.setOnNavigationItemSelectedListener(listener)
        coloredBottomNavigation.setOnNavigationItemSelectedListener(listener)

        removeItemButton.setOnClickListener {
            updateBottomNavigationItemCount(MenuItemAction.REMOVE)
        }
        addItemButton.setOnClickListener {
            updateBottomNavigationItemCount(MenuItemAction.ADD)
        }

        withBadgeBottomNavigation.apply {
            getOrCreateBadge(R.id.navigation_item_1)
            getOrCreateBadge(R.id.navigation_item_2).apply {
                number = 10
            }
            getOrCreateBadge(R.id.navigation_item_3).apply {
                number = 1000
            }
        }
    }

    private fun updateBottomNavigationItemCount(action: MenuItemAction) {
        val currentItemCount = bottomNavigation.visibleItemCount
        when (action) {
            MenuItemAction.ADD -> {
                bottomNavigation.setVisibleItem(currentItemCount, true)
                coloredBottomNavigation.setVisibleItem(currentItemCount, true)
                removeItemButton.isEnabled = true
                addItemButton.isEnabled = currentItemCount + 1 < MAX_ITEM_COUNT
            }
            MenuItemAction.REMOVE -> {
                bottomNavigation.setVisibleItem(currentItemCount - 1, false)
                coloredBottomNavigation.setVisibleItem(currentItemCount - 1, false)
                removeItemButton.isEnabled = currentItemCount - 1 > MIN_ITEM_COUNT
                addItemButton.isEnabled = true
            }
        }
    }

    private enum class MenuItemAction {
        ADD, REMOVE
    }

    companion object {
        private const val MIN_ITEM_COUNT = 3
        private const val MAX_ITEM_COUNT = 5
    }
}