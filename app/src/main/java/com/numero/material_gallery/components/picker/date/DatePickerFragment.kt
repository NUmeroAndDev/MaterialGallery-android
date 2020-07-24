package com.numero.material_gallery.components.picker.date

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.core.util.Pair
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.fragment_date_picker.*
import org.koin.android.ext.android.inject

class DatePickerFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_date_picker, container, false)
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
                    R.id.action_current_theme -> {
                        findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                        true
                    }
                    else -> false
                }
            }
        }
        showDatePickerButton.setOnClickListener {
            showDatePicker(isFullScreen = false)
        }
        showDateRangePickerButton.setOnClickListener {
            showDateRangePicker(isFullScreen = false)
        }
        showFullscreenDatePickerButton.setOnClickListener {
            showDatePicker(isFullScreen = true)
        }
        showFullscreenDateRangePickerButton.setOnClickListener {
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