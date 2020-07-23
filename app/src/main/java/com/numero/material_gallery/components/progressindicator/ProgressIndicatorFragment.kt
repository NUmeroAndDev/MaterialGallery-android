package com.numero.material_gallery.components.progressindicator

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.ProgressIndicator
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.fragment_progress_indicator.*
import org.koin.android.ext.android.inject

class ProgressIndicatorFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_progress_indicator, container, false)
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
                requireContext(),
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
}