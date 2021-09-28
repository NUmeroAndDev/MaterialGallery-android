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

        binding.fabVisibilityToggleGroup.addOnButtonCheckedListener { _, checkedId, _ ->
            val fabs = listOf(
                binding.primaryMiniFab,
                binding.primaryFab,
                binding.primaryLargeFab,
                binding.secondaryMiniFab,
                binding.secondaryFab,
                binding.secondaryLargeFab,
                binding.tertiaryMiniFab,
                binding.tertiaryFab,
                binding.tertiaryLargeFab,
                binding.surfaceMiniFab,
                binding.surfaceFab,
                binding.surfaceLargeFab,
            )
            when (checkedId) {
                R.id.fabShowButton -> fabs.forEach { it.show() }
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