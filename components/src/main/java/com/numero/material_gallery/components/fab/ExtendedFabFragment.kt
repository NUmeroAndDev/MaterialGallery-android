package com.numero.material_gallery.components.fab

import android.os.Bundle
import android.view.View
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentExtendedFabBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class ExtendedFabFragment : ComponentFragment(R.layout.fragment_extended_fab) {

    private val binding by viewBinding { FragmentExtendedFabBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extendedFabs = listOf(
            binding.primaryExtendedFab,
            binding.secondaryExtendedFab,
            binding.surfaceExtendedFab,
        )
        binding.fabStyleRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabStyleExtendRadioButton -> extendedFabs.forEach { it.extend() }
                else -> extendedFabs.forEach { it.shrink() }
            }
        }

        binding.fabVisibilityRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabShowRadioButton -> extendedFabs.forEach { it.show() }
                else -> extendedFabs.forEach { it.hide() }
            }
        }

        binding.rootLayout.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}