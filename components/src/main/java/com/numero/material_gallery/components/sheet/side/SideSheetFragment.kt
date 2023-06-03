package com.numero.material_gallery.components.sheet.side

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.sidesheet.SideSheetBehavior
import com.google.android.material.sidesheet.SideSheetCallback
import com.google.android.material.sidesheet.SideSheetDialog
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentSideSheetBinding
import com.numero.material_gallery.core.delegate.viewBinding

class SideSheetFragment : ComponentFragment(R.layout.fragment_side_sheet) {

    private val binding by viewBinding { FragmentSideSheetBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sheetBehavior = SideSheetBehavior.from(binding.sideSheetLayout).apply {
            addCallback(object : SideSheetCallback() {
                override fun onStateChanged(sheet: View, newState: Int) {
                    binding.showSideSheetButton.isEnabled =
                        newState == BottomSheetBehavior.STATE_HIDDEN
                    binding.showCoplanarSideSheetButton.isEnabled =
                        newState == BottomSheetBehavior.STATE_HIDDEN
                    binding.showModalSideSheetButton.isEnabled =
                        newState == BottomSheetBehavior.STATE_HIDDEN
                }

                override fun onSlide(sheet: View, slideOffset: Float) {
                }
            })
        }
        binding.showSideSheetButton.setOnClickListener {
            sheetBehavior.state = SideSheetBehavior.STATE_EXPANDED
        }
        binding.closeSideSheetButton.setOnClickListener {
            sheetBehavior.state = SideSheetBehavior.STATE_HIDDEN
        }

        val coplanarSheetBehavior = SideSheetBehavior.from(binding.coplanarSideSheetLayout).apply {
            addCallback(object : SideSheetCallback() {
                override fun onStateChanged(sheet: View, newState: Int) {
                    binding.showSideSheetButton.isEnabled =
                        newState == BottomSheetBehavior.STATE_HIDDEN
                    binding.showCoplanarSideSheetButton.isEnabled =
                        newState == BottomSheetBehavior.STATE_HIDDEN
                    binding.showModalSideSheetButton.isEnabled =
                        newState == BottomSheetBehavior.STATE_HIDDEN
                }

                override fun onSlide(sheet: View, slideOffset: Float) {
                }
            })
        }
        binding.showCoplanarSideSheetButton.setOnClickListener {
            coplanarSheetBehavior.state = SideSheetBehavior.STATE_EXPANDED
        }
        binding.closeCoplanarSideSheetButton.setOnClickListener {
            coplanarSheetBehavior.state = SideSheetBehavior.STATE_HIDDEN
        }

        binding.showModalSideSheetButton.setOnClickListener {
            showModalSideSheetDialog()
        }
    }

    private fun showModalSideSheetDialog() {
        val sideSheetDialog = SideSheetDialog(requireContext())
        sideSheetDialog.setContentView(R.layout.side_sheet_modal_layout)
        sideSheetDialog.findViewById<MaterialButton>(
            R.id.closeSideSheetButton
        )?.setOnClickListener {
            sideSheetDialog.hide()
        }
        sideSheetDialog.show()
    }
}