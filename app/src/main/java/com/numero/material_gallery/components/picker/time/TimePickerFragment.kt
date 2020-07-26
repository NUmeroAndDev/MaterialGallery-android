package com.numero.material_gallery.components.picker.time

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_time_picker.*
import java.text.SimpleDateFormat
import java.util.*

class TimePickerFragment : MaterialContainerTransformFragment(R.layout.fragment_time_picker) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        showTimePickerButton.setOnClickListener {
            showTimePicker()
        }
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

    private fun showTimePicker() {
        val isClock12H = timeFormatToggleGroup.checkedButtonId == R.id.timeFormat12HButton
        MaterialTimePicker.newInstance()
                .apply {
                    setTimeFormat(if (isClock12H) {
                        TimeFormat.CLOCK_12H
                    } else {
                        TimeFormat.CLOCK_24H
                    })
                    setListener {
                        showSelectedTime(it.hour, it.minute, isClock12H)
                    }
                }
                .show(childFragmentManager, TIME_PICKER_FRAGMENT_TAG)
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