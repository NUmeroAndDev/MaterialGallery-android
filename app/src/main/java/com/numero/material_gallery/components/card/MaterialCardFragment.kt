package com.numero.material_gallery.components.card

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.components.card.state.Corner
import com.numero.material_gallery.components.card.state.Elevation
import com.numero.material_gallery.components.card.state.Stroke
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.fragment_material_card.*
import org.koin.android.ext.android.inject

class MaterialCardFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_material_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}