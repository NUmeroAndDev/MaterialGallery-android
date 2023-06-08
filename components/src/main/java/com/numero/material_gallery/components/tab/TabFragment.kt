package com.numero.material_gallery.components.tab

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentTabBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class TabFragment : ComponentFragment(R.layout.fragment_tab) {

    private val binding by viewBinding { FragmentTabBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTab()

        binding.root.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }

    private fun setupTab() {
        val tabs = listOf(
            binding.defaultTab,
            binding.onSurfaceTab,
            binding.secondaryTab,
        )
        binding.indicatorAnimationToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            tabs.forEach {
                it.tabIndicatorAnimationMode = when (checkedId) {
                    R.id.linearButton -> TabLayout.INDICATOR_ANIMATION_MODE_LINEAR
                    R.id.elasticButton -> TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC
                    R.id.fadeButton -> TabLayout.INDICATOR_ANIMATION_MODE_FADE
                    else -> throw IllegalArgumentException()
                }
            }
        }

        binding.inlineSwitch.setOnCheckedChangeListener { _, isChecked ->
            tabs.forEach {
                it.isInlineLabel = isChecked
            }
        }
    }
}