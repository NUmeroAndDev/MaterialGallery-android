package com.numero.material_gallery.components.slider

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.fragment_slider.*
import org.koin.android.ext.android.inject

class SliderFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.menu_common)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_info -> {
                        findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                        true
                    }
                    else -> false
                }
            }
        }

        val valueFormat = "%.0f"
        defaultSlider.addOnChangeListener { _, value, _ ->
            defaultSliderValueText.text = valueFormat.format(value)
        }
        defaultSliderValueText.text = valueFormat.format(defaultSlider.value)

        discreteSlider.addOnChangeListener { _, value, _ ->
            discreteSliderValueText.text = valueFormat.format(value)
        }
        discreteSliderValueText.text = valueFormat.format(discreteSlider.value)
        /**
         * FIXME : Can't resolve
         * https://github.com/material-components/material-components-android/issues/1342
         */
        //discreteSlider.setLabelFormatter(Slider.BasicLabelFormatter())

        labelFormatterSlider.addOnChangeListener { _, value, _ ->
            labelFormatterSliderValueText.text = valueFormat.format(value)
        }
        labelFormatterSliderValueText.text = valueFormat.format(labelFormatterSlider.value)
        //labelFormatterSlider.setLabelFormatter(Slider.BasicLabelFormatter())

        val rangeValueFormat = "From:%.0f\nTo:%.0f"
        rangeSlider.addOnChangeListener { slider, _, _ ->
            rangeSliderValueText.text = rangeValueFormat.format(slider.values.first(), slider.values.last())
        }
        rangeSliderValueText.text = rangeValueFormat.format(rangeSlider.values.first(), rangeSlider.values.last())
    }
}