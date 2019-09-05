package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_date_picker.*
import org.koin.android.ext.android.inject

class DatePickerActivity : AppCompatActivity(R.layout.activity_date_picker) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_common, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                ThemeInfoBottomSheetDialog.newInstance().showIfNeed(supportFragmentManager)
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
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
        val dataPickerFragment = if (supportFragmentManager.findFragmentByTag(DATE_PICKER_FRAGMENT_TAG) == null) {
            MaterialDatePicker.Builder.datePicker().apply {
                if (isFullScreen) {
                    setTheme(resolveAttributeOrThrow(R.attr.materialCalendarFullscreenTheme))
                }
            }.build()
        } else {
            supportFragmentManager.findFragmentByTag(DATE_PICKER_FRAGMENT_TAG) as MaterialDatePicker<Long>
        }
        dataPickerFragment.apply {
            addOnPositiveButtonClickListener {
                Toast.makeText(context, headerText, Toast.LENGTH_LONG).show()
            }
        }.show(supportFragmentManager, DATE_PICKER_FRAGMENT_TAG)
    }

    private fun showDateRangePicker(isFullScreen: Boolean = false) {
        val dataPickerFragment = if (supportFragmentManager.findFragmentByTag(DATE_RANGE_PICKER_FRAGMENT_TAG) == null) {
            MaterialDatePicker.Builder.dateRangePicker().apply {
                if (isFullScreen.not()) {
                    setTheme(resolveAttributeOrThrow(R.attr.materialCalendarTheme))
                }
            }.build()
        } else {
            supportFragmentManager.findFragmentByTag(DATE_RANGE_PICKER_FRAGMENT_TAG) as MaterialDatePicker<Pair<Long, Long>>
        }
        dataPickerFragment.apply {
            addOnPositiveButtonClickListener { time: Pair<Long, Long> ->
                Toast.makeText(context, headerText, Toast.LENGTH_LONG).show()
            }
        }.show(supportFragmentManager, DATE_RANGE_PICKER_FRAGMENT_TAG)
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

        fun createIntent(context: Context): Intent = Intent(context, DatePickerActivity::class.java)
    }
}