package com.numero.material_gallery.components.progressindicator

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.progressindicator.*
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentProgressIndicatorBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class ProgressIndicatorFragment : ComponentFragment(R.layout.fragment_progress_indicator) {

    private val binding by viewBinding { FragmentProgressIndicatorBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressIndicatorSpec = CircularProgressIndicatorSpec(
            requireContext(),
            null,
            0,
            R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall
        )
        val indeterminateDrawable = IndeterminateDrawable.createCircularDrawable(
            requireContext(),
            progressIndicatorSpec
        )
        binding.indeterminateProgressChip.chipIcon = indeterminateDrawable

        val forButtonIndeterminateDrawable = IndeterminateDrawable.createCircularDrawable(
            requireContext(),
            progressIndicatorSpec
        )
        binding.indeterminateProgressButton.icon = forButtonIndeterminateDrawable

        binding.progressSlider.addOnChangeListener { _, value, _ ->
            binding.extraSmallDeterminateCircularProgressIndicator.progress = value.toInt()
            binding.smallDeterminateCircularProgressIndicator.progress = value.toInt()
            binding.determinateCircularProgressIndicator.progress = value.toInt()
            binding.determinateLinearProgressIndicator.progress = value.toInt()
        }

        val indicators = listOf(
            binding.extraSmallIndeterminateCircularProgressIndicator,
            binding.smallIndeterminateCircularProgressIndicator,
            binding.indeterminateCircularProgressIndicator,
            binding.indeterminateLinearProgressIndicator,
            binding.extraSmallDeterminateCircularProgressIndicator,
            binding.smallDeterminateCircularProgressIndicator,
            binding.determinateCircularProgressIndicator,
            binding.determinateLinearProgressIndicator,
        )
        binding.visibilityProgressIndicatorToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked.not()) return@addOnButtonCheckedListener
            indicators.forEach {
                when (checkedId) {
                    R.id.show -> it.show()
                    R.id.hide -> it.hide()
                }
            }
        }

        val animationBehaviors = listOf(
            BaseProgressIndicator.SHOW_NONE to "NONE",
            BaseProgressIndicator.SHOW_INWARD to "INWARD",
            BaseProgressIndicator.SHOW_OUTWARD to "OUTWARD"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            animationBehaviors.map {
                it.second
            }
        )
        binding.grownModeTextView.setText(animationBehaviors.first().second)
        binding.grownModeTextView.setAdapter(adapter)
        binding.grownModeTextView.setOnItemClickListener { _, _, position, _ ->
            val animationBehavior = animationBehaviors[position].first
            indicators.forEach {
                it.showAnimationBehavior = animationBehavior
                it.hideAnimationBehavior = animationBehavior
            }
        }

        binding.inverseSwitch.setOnCheckedChangeListener { _, isChecked ->
            indicators.forEach {
                when (it) {
                    is CircularProgressIndicator -> {
                        it.indicatorDirection = if (isChecked) {
                            CircularProgressIndicator.INDICATOR_DIRECTION_COUNTERCLOCKWISE
                        } else {
                            CircularProgressIndicator.INDICATOR_DIRECTION_CLOCKWISE
                        }
                    }
                    is LinearProgressIndicator -> {
                        it.indicatorDirection = if (isChecked) {
                            LinearProgressIndicator.INDICATOR_DIRECTION_END_TO_START
                        } else {
                            LinearProgressIndicator.INDICATOR_DIRECTION_START_TO_END
                        }
                    }
                }
            }
        }

        binding.root.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}