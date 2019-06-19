package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.model.state.card.Corner
import com.numero.material_gallery.model.state.card.Elevation
import com.numero.material_gallery.model.state.card.Stroke
import com.numero.material_gallery.repository.IConfigRepository
import com.numero.material_gallery.view.SelectionCardAdapter
import kotlinx.android.synthetic.main.activity_material_card.*
import org.koin.android.ext.android.inject

class MaterialCardActivity : AppCompatActivity(R.layout.activity_material_card) {

    private val configRepository by inject<IConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        elevationSeekBar.addOnSeekBarChangeListener(
                onProgressChanged = { _, progress, _ ->
                    if (progress >= Elevation.values().size) {
                        return@addOnSeekBarChangeListener
                    }
                    updateElevation(Elevation.values()[progress])
                }
        )
        cornerSeekBar.addOnSeekBarChangeListener(
                onProgressChanged = { _, progress, _ ->
                    updateCorner(Corner.values()[progress])
                }
        )
        strokeSeekBar.addOnSeekBarChangeListener(
                onProgressChanged = { _, progress, _ ->
                    updateStroke(Stroke.values()[progress])
                }
        )

        updateElevation(Elevation.values()[elevationSeekBar.progress])
        updateCorner(Corner.values()[cornerSeekBar.progress])
        updateStroke(Stroke.values()[strokeSeekBar.progress])

        setupSelectionCardList()
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

    private fun setupSelectionCardList() {
        selectionCardListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MaterialCardActivity, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = SelectionCardAdapter()
        }
    }

    private fun updateElevation(elevation: Elevation) {
        val elevationSize = resources.getDimension(elevation.dimenRes)
        materialCardView.cardElevation = elevationSize
        elevationInfoTextView.text = getString(R.string.elevation_info_format).format(convertPxToDp(elevationSize).toInt())
    }

    private fun updateCorner(corner: Corner) {
        val cornerSize = resources.getDimension(corner.dimenRes)
        materialCardView.radius = cornerSize
        cornerInfoTextView.text = getString(R.string.corner_info_format).format(convertPxToDp(cornerSize).toInt())
    }

    private fun updateStroke(stroke: Stroke) {
        val strokeSize = resources.getDimensionPixelSize(stroke.dimenRes)
        materialCardView.strokeWidth = strokeSize
        strokeInfoTextView.text = getString(R.string.stroke_info_format).format(convertPxToDp(strokeSize).toInt())
    }

    private fun convertPxToDp(px: Float): Float {
        val metrics = resources.displayMetrics
        return px / metrics.density
    }

    private fun convertPxToDp(px: Int): Float {
        val metrics = resources.displayMetrics
        return px / metrics.density
    }

    private inline fun SeekBar.addOnSeekBarChangeListener(
            crossinline onProgressChanged: (
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean) -> Unit = { _, _, _ -> },
            crossinline onStartTrackingTouch: (seekBar: SeekBar?) -> Unit = {},
            crossinline onStopTrackingTouch: (seekBar: SeekBar?) -> Unit = {}
    ): SeekBar.OnSeekBarChangeListener {
        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                onProgressChanged(seekBar, progress, fromUser)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                onStartTrackingTouch(seekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                onStopTrackingTouch(seekBar)
            }
        }
        setOnSeekBarChangeListener(listener)
        return listener
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MaterialCardActivity::class.java)
    }
}