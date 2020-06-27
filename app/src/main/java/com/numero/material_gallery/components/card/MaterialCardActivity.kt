package com.numero.material_gallery.components.card

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.components.card.state.Corner
import com.numero.material_gallery.components.card.state.Elevation
import com.numero.material_gallery.components.card.state.Stroke
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_material_card.*
import org.koin.android.ext.android.inject

class MaterialCardActivity : AppCompatActivity(R.layout.activity_material_card) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        elevationSlider.addOnChangeListener { _, value, _ ->
            updateElevation(Elevation.values()[value.toInt()])
        }
        cornerSlider.addOnChangeListener { _, value, _ ->
            updateCorner(Corner.values()[value.toInt()])
        }
        strokeSlider.addOnChangeListener { _, value, _ ->
            updateStroke(Stroke.values()[value.toInt()])
        }

        setupSlidersLabelFormatter()

        updateElevation(Elevation.values()[elevationSlider.value.toInt()])
        updateCorner(Corner.values()[cornerSlider.value.toInt()])
        updateStroke(Stroke.values()[strokeSlider.value.toInt()])

        setupSelectionCardList()
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

    private fun setupSlidersLabelFormatter() {
        elevationSlider.setLabelFormatter {
            val elevation = Elevation.values()[it.toInt()]
            val elevationSize = resources.getDimension(elevation.dimenRes)
            "%ddp".format(convertPxToDp(elevationSize).toInt())
        }
        cornerSlider.setLabelFormatter {
            val corner = Corner.values()[it.toInt()]
            val cornerSize = resources.getDimension(corner.dimenRes)
            "%ddp".format(convertPxToDp(cornerSize).toInt())
        }
        strokeSlider.setLabelFormatter {
            val stroke = Stroke.values()[it.toInt()]
            val strokeSize = resources.getDimensionPixelSize(stroke.dimenRes)
            "%ddp".format(convertPxToDp(strokeSize).toInt())
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

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MaterialCardActivity::class.java)
    }
}