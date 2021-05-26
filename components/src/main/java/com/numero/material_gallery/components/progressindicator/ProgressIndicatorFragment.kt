package com.numero.material_gallery.components.progressindicator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.*
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentProgressIndicatorBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import dev.chrisbanes.insetter.applyInsetter

class ProgressIndicatorFragment :
    MaterialContainerTransformFragment(R.layout.fragment_progress_indicator) {

    private var _binding: FragmentProgressIndicatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProgressIndicatorBinding.bind(view)

        val progressIndicatorSpec = CircularProgressIndicatorSpec(
            requireContext(),
            null,
            0,
            R.style.Widget_MaterialComponents_CircularProgressIndicator_ExtraSmall
        )

        val indeterminateDrawable = IndeterminateDrawable.createCircularDrawable(
            requireContext(),
            progressIndicatorSpec
        )
        binding.indeterminateProgressChip.chipIcon = indeterminateDrawable

        binding.progressSlider.addOnChangeListener { _, value, _ ->
            binding.determinateCircularProgressIndicator.progress = value.toInt()
            binding.determinateLinearProgressIndicator.progress = value.toInt()
        }

        binding.visibilityProgressIndicatorToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked.not()) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.show -> {
                    binding.indeterminateCircularProgressIndicator.show()
                    binding.indeterminateLinearProgressIndicator.show()
                    binding.roundedCircularProgressIndicator.show()
                    binding.roundedLinearProgressIndicator.show()
                    binding.inverseCircularProgressIndicator.show()
                    binding.inverseLinearProgressIndicator.show()
                    binding.determinateCircularProgressIndicator.show()
                    binding.determinateLinearProgressIndicator.show()
                }
                R.id.hide -> {
                    binding.indeterminateCircularProgressIndicator.hide()
                    binding.indeterminateLinearProgressIndicator.hide()
                    binding.roundedCircularProgressIndicator.hide()
                    binding.roundedLinearProgressIndicator.hide()
                    binding.inverseCircularProgressIndicator.hide()
                    binding.inverseLinearProgressIndicator.hide()
                    binding.determinateCircularProgressIndicator.hide()
                    binding.determinateLinearProgressIndicator.hide()
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
            binding.indeterminateCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.indeterminateLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.roundedCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.roundedLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.inverseCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.inverseLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.determinateCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            binding.determinateLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
        }

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
}