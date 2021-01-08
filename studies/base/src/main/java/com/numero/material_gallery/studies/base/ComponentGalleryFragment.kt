package com.numero.material_gallery.studies.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialContainerTransform
import com.numero.material_gallery.base.databinding.FragmentComponentGalleryBinding

abstract class ComponentGalleryFragment(
    @StyleRes private val overlayTheme: Int
) : Fragment() {

    abstract val transitionNameRes: Int

    private var _binding: FragmentComponentGalleryBinding? = null
    private val binding get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val wrappedContext = ContextThemeWrapper(requireContext(), overlayTheme)
        _binding = FragmentComponentGalleryBinding.inflate(
            LayoutInflater.from(wrappedContext),
            container,
            false
        )
        binding.root.transitionName = getString(transitionNameRes)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        binding.showDialogButton.setOnClickListener {
            showDialog()
        }
        setupSnackbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        val wrappedContext = ContextThemeWrapper(requireContext(), overlayTheme)
        MaterialAlertDialogBuilder(wrappedContext)
            .setTitle("Title")
            .setMessage("Message")
            .setPositiveButton("Ok", null)
            .setNegativeButton("Cancel", null)
            .show()
    }
}
