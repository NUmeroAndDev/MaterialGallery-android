package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_slider.*
import org.koin.android.ext.android.inject

class SliderActivity : AppCompatActivity(R.layout.activity_slider) {

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
                ThemeInfoBottomSheetDialog.newInstance().showIfNeeded(supportFragmentManager)
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

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, SliderActivity::class.java)
    }
}