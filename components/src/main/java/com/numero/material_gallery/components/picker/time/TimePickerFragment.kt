package com.numero.material_gallery.components.picker.time

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentTimePickerBinding
import com.numero.material_gallery.core.delegate.viewBinding
import java.text.SimpleDateFormat
import java.util.*

class TimePickerFragment : ComponentFragment(R.layout.fragment_time_picker) {

    private val binding by viewBinding { FragmentTimePickerBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.showTimePickerButton.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showTimePicker() {
        val isClock12H = binding.timeFormatToggleGroup.checkedButtonId == R.id.timeFormat12HButton
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(
                if (isClock12H) {
                    TimeFormat.CLOCK_12H
                } else {
                    TimeFormat.CLOCK_24H
                }
            )
            .build()
        timePicker.addOnPositiveButtonClickListener {
            showSelectedTime(
                timePicker.hour,
                timePicker.minute,
                isClock12H
            )
        }
        timePicker.show(childFragmentManager, TIME_PICKER_FRAGMENT_TAG)
    }

    private fun showSelectedTime(hour: Int, min: Int, isClock12H: Boolean) {
        val date = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }.time
        val pattern = if (isClock12H) {
            "hh:mm a"
        } else {
            "HH:mm"
        }
        val formatter = SimpleDateFormat(pattern, Locale.US)

        Toast.makeText(
            context,
            formatter.format(date),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val TIME_PICKER_FRAGMENT_TAG = "TIME_PICKER_FRAGMENT_TAG"
    }
}