package com.numero.material_gallery.components.picker.date

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentDatePickerBinding
import com.numero.material_gallery.core.delegate.viewBinding

class DatePickerFragment : ComponentFragment(R.layout.fragment_date_picker) {

    private val binding by viewBinding { FragmentDatePickerBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.showDatePickerButton.setOnClickListener {
            showDatePicker(isFullScreen = false)
        }
        binding.showDateRangePickerButton.setOnClickListener {
            showDateRangePicker(isFullScreen = false)
        }
        binding.showFullscreenDatePickerButton.setOnClickListener {
            showDatePicker(isFullScreen = true)
        }
        binding.showFullscreenDateRangePickerButton.setOnClickListener {
            showDateRangePicker(isFullScreen = true)
        }
    }

    private fun showDatePicker(isFullScreen: Boolean = false) {
        val dataPickerFragment = if (childFragmentManager.findFragmentByTag(DATE_PICKER_FRAGMENT_TAG) == null) {
            MaterialDatePicker.Builder.datePicker().apply {
                if (isFullScreen) {
                    setTheme(requireContext().resolveAttributeOrThrow(R.attr.materialCalendarFullscreenTheme))
                }
            }.build()
        } else {
            childFragmentManager.findFragmentByTag(DATE_PICKER_FRAGMENT_TAG) as MaterialDatePicker<Long>
        }
        dataPickerFragment.apply {
            addOnPositiveButtonClickListener {
                Toast.makeText(context, headerText, Toast.LENGTH_LONG).show()
            }
        }.show(childFragmentManager, DATE_PICKER_FRAGMENT_TAG)
    }

    private fun showDateRangePicker(isFullScreen: Boolean = false) {
        val dataPickerFragment = if (childFragmentManager.findFragmentByTag(DATE_RANGE_PICKER_FRAGMENT_TAG) == null) {
            MaterialDatePicker.Builder.dateRangePicker().apply {
                if (isFullScreen.not()) {
                    setTheme(requireContext().resolveAttributeOrThrow(R.attr.materialCalendarTheme))
                }
            }.build()
        } else {
            childFragmentManager.findFragmentByTag(DATE_RANGE_PICKER_FRAGMENT_TAG) as MaterialDatePicker<Pair<Long, Long>>
        }
        dataPickerFragment.apply {
            addOnPositiveButtonClickListener { time: Pair<Long, Long> ->
                Toast.makeText(context, headerText, Toast.LENGTH_LONG).show()
            }
        }.show(childFragmentManager, DATE_RANGE_PICKER_FRAGMENT_TAG)
    }

    private fun Context.resolveAttributeOrThrow(@AttrRes attributeResId: Int): Int {
        val typedValue = TypedValue()
        if (theme.resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue.data
        }
        throw IllegalArgumentException(resources.getResourceName(attributeResId))
    }

    companion object {
        private const val DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG"
        private const val DATE_RANGE_PICKER_FRAGMENT_TAG = "DATE_RANGE_PICKER_FRAGMENT_TAG"
    }
}