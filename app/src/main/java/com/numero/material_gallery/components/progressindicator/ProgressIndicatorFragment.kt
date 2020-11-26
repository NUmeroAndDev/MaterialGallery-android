package com.numero.material_gallery.components.progressindicator

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.*
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import kotlinx.android.synthetic.main.fragment_progress_indicator.*

class ProgressIndicatorFragment : MaterialContainerTransformFragment(R.layout.fragment_progress_indicator) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressIndicatorSpec = CircularProgressIndicatorSpec(
                requireContext(),
                null
        ).apply {
            indicatorInset = resources.getDimensionPixelSize(R.dimen.circular_progress_in_chip_indicator_inset)
            indicatorSize = resources.getDimensionPixelSize(R.dimen.circular_progress_in_chip_indicator_size)
            trackThickness = resources.getDimensionPixelSize(R.dimen.circular_progress_in_chip_track_thickness)
        }

        val indeterminateDrawable = IndeterminateDrawable.createCircularDrawable(
                requireContext(),
                progressIndicatorSpec
        )
        indeterminateProgressChip.chipIcon = indeterminateDrawable

        progressSlider.addOnChangeListener { _, value, _ ->
            determinateCircularProgressIndicator.progress = value.toInt()
            determinateLinearProgressIndicator.progress = value.toInt()
        }

        visibilityProgressIndicatorToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked.not()) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.show -> {
                    indeterminateCircularProgressIndicator.show()
                    indeterminateLinearProgressIndicator.show()
                    inverseCircularProgressIndicator.show()
                    inverseLinearProgressIndicator.show()
                    determinateCircularProgressIndicator.show()
                    determinateLinearProgressIndicator.show()
                }
                R.id.hide -> {
                    indeterminateCircularProgressIndicator.hide()
                    indeterminateLinearProgressIndicator.hide()
                    inverseCircularProgressIndicator.hide()
                    inverseLinearProgressIndicator.hide()
                    determinateCircularProgressIndicator.hide()
                    determinateLinearProgressIndicator.hide()
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
        grownModeTextView.setText(animationBehaviors.first().second)
        grownModeTextView.setAdapter(adapter)
        grownModeTextView.setOnItemClickListener { _, _, position, _ ->
            val animationBehavior = animationBehaviors[position].first
            indeterminateCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            indeterminateLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            roundedCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            roundedLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            inverseCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            inverseLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            determinateCircularProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
            determinateLinearProgressIndicator.apply {
                showAnimationBehavior = animationBehavior
                hideAnimationBehavior = animationBehavior
            }
        }

        scrollView.applySystemWindowInsetsPadding(applyBottom = true)
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