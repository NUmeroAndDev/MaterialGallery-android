package com.numero.material_gallery.components.progressindicator

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.IndeterminateDrawable
import com.google.android.material.progressindicator.ProgressIndicator
import com.google.android.material.progressindicator.ProgressIndicatorSpec
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

        val progressIndicatorSpec = ProgressIndicatorSpec().apply {
            loadFromAttributes(
                    requireContext(),
                    null,
                    R.style.Widget_MaterialComponents_ProgressIndicator_Circular_Indeterminate
            )
            circularInset = 0
            circularRadius = resources.getDimensionPixelSize(R.dimen.indicator_in_chip_circular_radius)
        }
        val indeterminateDrawable = IndeterminateDrawable(
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

        val growModes = listOf(
                ProgressIndicator.GROW_MODE_NONE to "NONE",
                ProgressIndicator.GROW_MODE_INCOMING to "INCOMING",
                ProgressIndicator.GROW_MODE_OUTGOING to "OUTGOING",
                ProgressIndicator.GROW_MODE_BIDIRECTIONAL to "BIDIRECTIONAL"
        )
        val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                growModes.map {
                    it.second
                }
        )
        grownModeTextView.setText(growModes.first().second)
        grownModeTextView.setAdapter(adapter)
        grownModeTextView.setOnItemClickListener { _, _, position, _ ->
            val growMode = growModes[position].first
            indeterminateCircularProgressIndicator.growMode = growMode
            indeterminateLinearProgressIndicator.growMode = growMode
            inverseCircularProgressIndicator.growMode = growMode
            inverseLinearProgressIndicator.growMode = growMode
            determinateCircularProgressIndicator.growMode = growMode
            determinateLinearProgressIndicator.growMode = growMode
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