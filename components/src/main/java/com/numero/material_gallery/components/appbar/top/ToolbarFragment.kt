package com.numero.material_gallery.components.appbar.top

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentToolbarBinding
import com.numero.material_gallery.core.delegate.viewBinding

class ToolbarFragment : ComponentFragment(R.layout.fragment_toolbar) {

    private val binding by viewBinding { FragmentToolbarBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun initViews() {
        val toolbarList = listOf(
            binding.defaultToolbar,
            binding.onSurfaceToolbar,
            binding.surfaceToolbar,
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