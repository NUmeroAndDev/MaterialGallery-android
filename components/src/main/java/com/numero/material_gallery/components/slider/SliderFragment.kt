package com.numero.material_gallery.components.slider

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.BasicLabelFormatter
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentSliderBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class SliderFragment : MaterialContainerTransformFragment(R.layout.fragment_slider) {

    private val binding by viewBinding { FragmentSliderBinding.bind(it) }

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
        binding.defaultSlider.addOnChangeListener { _, value, _ ->
            binding.defaultSliderValueText.text = valueFormat.format(value)
        }
        binding.defaultSliderValueText.text = valueFormat.format(binding.defaultSlider.value)

        binding.discreteSlider.addOnChangeListener { _, value, _ ->
            binding.discreteSliderValueText.text = valueFormat.format(value)
        }
        binding.discreteSliderValueText.text = valueFormat.format(binding.discreteSlider.value)
        binding.discreteSlider.setLabelFormatter(BasicLabelFormatter())

        binding.labelFormatterSlider.addOnChangeListener { _, value, _ ->
            binding.labelFormatterSliderValueText.text = valueFormat.format(value)
        }
        binding.labelFormatterSliderValueText.text =
            valueFormat.format(binding.labelFormatterSlider.value)
        binding.labelFormatterSlider.setLabelFormatter(BasicLabelFormatter())

        val rangeValueFormat = "From:%.0f\nTo:%.0f"
        binding.rangeSlider.addOnChangeListener { slider, _, _ ->
            binding.rangeSliderValueText.text = rangeValueFormat.format(
                slider.values.first(),
                slider.values.last()
            )
        }
        binding.rangeSliderValueText.text = rangeValueFormat.format(
            binding.rangeSlider.values.first(),
            binding.rangeSlider.values.last()
        )

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}