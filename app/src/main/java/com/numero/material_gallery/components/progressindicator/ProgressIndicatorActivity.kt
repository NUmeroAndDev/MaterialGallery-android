package com.numero.material_gallery.components.progressindicator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.ProgressIndicator
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_progress_indicator.*
import org.koin.android.ext.android.inject

class ProgressIndicatorActivity : AppCompatActivity(R.layout.activity_progress_indicator) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

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
                this,
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

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ProgressIndicatorActivity::class.java)
    }
}