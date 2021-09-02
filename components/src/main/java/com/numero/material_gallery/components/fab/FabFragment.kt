package com.numero.material_gallery.components.fab

import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentFabBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class FabFragment : ComponentFragment(R.layout.fragment_fab) {

    private val binding by viewBinding { FragmentFabBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabs = listOf(
            binding.primaryFab,
            binding.secondaryFab,
            binding.surfaceFab,
        )
        binding.fabSizeRadioGroup.setOnCheckedChangeListener { _, id ->
            fabs.forEach {
                it.size = when (id) {
                    R.id.fabSizeMinRadioButton -> FloatingActionButton.SIZE_MINI
                    else -> FloatingActionButton.SIZE_NORMAL
                }
            }
        }
        binding.fabVisibilityRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabShowRadioButton -> fabs.forEach { it.show() }
                else -> fabs.forEach { it.hide() }
            }
        }

        binding.rootLayout.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}