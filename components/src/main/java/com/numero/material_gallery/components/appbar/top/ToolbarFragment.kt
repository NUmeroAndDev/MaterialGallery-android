package com.numero.material_gallery.components.appbar.top

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentToolbarBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding

class ToolbarFragment : MaterialContainerTransformFragment(R.layout.fragment_toolbar) {

    private val binding by viewBinding { FragmentToolbarBinding.bind(it) }

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

    @SuppressLint("UnsafeExperimentalUsageError")
    private fun initViews() {
        val toolbarList = listOf(
            binding.primaryToolbar,
            binding.surfaceToolbar,
            binding.primarySurfaceToolbar
        )
        toolbarList.forEach { toolbar ->
            toolbar.inflateMenu(R.menu.menu_action_bar)

            val badge = BadgeDrawable.create(requireContext())
            BadgeUtils.attachBadgeDrawable(badge, toolbar, R.id.action_info)
        }

        binding.centerTitleSwitch.setOnCheckedChangeListener { _, isChecked ->
            toolbarList.forEach { toolbar ->
                toolbar.isTitleCentered = isChecked
            }
        }
    }
}