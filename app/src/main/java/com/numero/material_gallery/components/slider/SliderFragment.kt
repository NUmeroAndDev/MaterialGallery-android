package com.numero.material_gallery.components.slider

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.BasicLabelFormatter
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import kotlinx.android.synthetic.main.fragment_slider.*

class SliderFragment : MaterialContainerTransformFragment(R.layout.fragment_slider) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
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

    private fun setupViews() {
        val valueFormat = "%.0f"
        defaultSlider.addOnChangeListener { _, value, _ ->
            defaultSliderValueText.text = valueFormat.format(value)
        }
        defaultSliderValueText.text = valueFormat.format(defaultSlider.value)

        discreteSlider.addOnChangeListener { _, value, _ ->
            discreteSliderValueText.text = valueFormat.format(value)
        }
        discreteSliderValueText.text = valueFormat.format(discreteSlider.value)
        discreteSlider.setLabelFormatter(BasicLabelFormatter())

        labelFormatterSlider.addOnChangeListener { _, value, _ ->
            labelFormatterSliderValueText.text = valueFormat.format(value)
        }
        labelFormatterSliderValueText.text = valueFormat.format(labelFormatterSlider.value)
        labelFormatterSlider.setLabelFormatter(BasicLabelFormatter())

        val rangeValueFormat = "From:%.0f\nTo:%.0f"
        rangeSlider.addOnChangeListener { slider, _, _ ->
            rangeSliderValueText.text = rangeValueFormat.format(slider.values.first(), slider.values.last())
        }
        rangeSliderValueText.text = rangeValueFormat.format(rangeSlider.values.first(), rangeSlider.values.last())

        scrollView.applySystemWindowInsetsPadding(applyBottom = true)
    }
}