package com.numero.material_gallery.studies.material3

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.numero.material_gallery.core.applyFloatingActionButtonEdgeTreatment
import com.numero.material_gallery.core.delegate.viewBinding
import com.numero.material_gallery.studies.material3.databinding.FragmentMaterial3ComponentBinding
import dev.chrisbanes.insetter.applyInsetter

class MaterialComponentFragment : Fragment(R.layout.fragment_material3_component) {

    private val binding by viewBinding { FragmentMaterial3ComponentBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            val backgroundColor = MaterialColors.getColor(
                requireContext(),
                android.R.attr.colorBackground,
                "The attribute is not set in the current theme"
            )
            setAllContainerColors(backgroundColor)
            scrimColor = Color.TRANSPARENT
        }
        exitTransition = Hold()
        reenterTransition = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        binding.appbar.applyInsetter {
            type(statusBars = true) {
                padding(top = true)
            }
        }
        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding(bottom = true)
            }
        }

        binding.bottomAppBar.applyFloatingActionButtonEdgeTreatment(binding.fab)
        binding.showDialogButton.setOnClickListener {
            showDialog()
        }
        setupSnackbar()
    }

    @SuppressLint("ShowToast")
    private fun setupSnackbar() {
        val snackbarView = Snackbar.make(
            binding.snackbarContainer, "Message", Snackbar.LENGTH_INDEFINITE
        )
            .setAction("Action") {
            }
            .view
        binding.snackbarContainer.addView(snackbarView)
    }

    private fun showDialog() {
        val wrappedContext = ContextThemeWrapper(requireContext(), R.style.Theme_Material3_DayNight)
        MaterialAlertDialogBuilder(wrappedContext)
            .setTitle("Title")
            .setMessage("Message")
            .setPositiveButton("Ok", null)
            .setNegativeButton("Cancel", null)
            .show()
    }
}