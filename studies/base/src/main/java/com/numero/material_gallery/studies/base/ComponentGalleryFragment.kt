package com.numero.material_gallery.studies.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.base.databinding.FragmentComponentGalleryBinding

abstract class ComponentGalleryFragment(
    @StyleRes private val overlayTheme: Int
) : Fragment() {

    private var _binding: FragmentComponentGalleryBinding? = null
    private val binding get() = _binding!!

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
