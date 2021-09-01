package com.numero.material_gallery.components.card

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.card.state.Corner
import com.numero.material_gallery.components.card.state.Elevation
import com.numero.material_gallery.components.card.state.Stroke
import com.numero.material_gallery.components.databinding.FragmentMaterialCardBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class MaterialCardFragment : MaterialContainerTransformFragment(R.layout.fragment_material_card) {

    private val binding by viewBinding { FragmentMaterialCardBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.elevationSlider.addOnChangeListener { _, value, _ ->
            updateElevation(Elevation.values()[value.toInt()])
        }
        binding.cornerSlider.addOnChangeListener { _, value, _ ->
            updateCorner(Corner.values()[value.toInt()])
        }
        binding.strokeSlider.addOnChangeListener { _, value, _ ->
            updateStroke(Stroke.values()[value.toInt()])
        }

        setupSlidersLabelFormatter()

        updateElevation(Elevation.values()[binding.elevationSlider.value.toInt()])
        updateCorner(Corner.values()[binding.cornerSlider.value.toInt()])
        updateStroke(Stroke.values()[binding.strokeSlider.value.toInt()])

        setupSelectionCardList()

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
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

    private fun setupSlidersLabelFormatter() {
        binding.elevationSlider.setLabelFormatter {
            val elevation = Elevation.values()[it.toInt()]
            val elevationSize = resources.getDimension(elevation.dimenRes)
            "%ddp".format(convertPxToDp(elevationSize).toInt())
        }
        binding.cornerSlider.setLabelFormatter {
            val corner = Corner.values()[it.toInt()]
            val cornerSize = resources.getDimension(corner.dimenRes)
            "%ddp".format(convertPxToDp(cornerSize).toInt())
        }
        binding.strokeSlider.setLabelFormatter {
            val stroke = Stroke.values()[it.toInt()]
            val strokeSize = resources.getDimensionPixelSize(stroke.dimenRes)
            "%ddp".format(convertPxToDp(strokeSize).toInt())
        }
    }

    private fun setupSelectionCardList() {
        binding.selectionCardListRecyclerView.apply {
            setHasFixedSize(true)
            adapter = SelectionCardAdapter()
        }
    }

    private fun updateElevation(elevation: Elevation) {
        val elevationSize = resources.getDimension(elevation.dimenRes)
        binding.materialCardView.cardElevation = elevationSize
        binding.elevationInfoTextView.text =
            getString(R.string.elevation_info_format).format(convertPxToDp(elevationSize).toInt())
    }

    private fun updateCorner(corner: Corner) {
        val cornerSize = resources.getDimension(corner.dimenRes)
        binding.materialCardView.radius = cornerSize
        binding.cornerInfoTextView.text =
            getString(R.string.corner_info_format).format(convertPxToDp(cornerSize).toInt())
    }

    private fun updateStroke(stroke: Stroke) {
        val strokeSize = resources.getDimensionPixelSize(stroke.dimenRes)
        binding.materialCardView.strokeWidth = strokeSize
        binding.strokeInfoTextView.text =
            getString(R.string.stroke_info_format).format(convertPxToDp(strokeSize).toInt())
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