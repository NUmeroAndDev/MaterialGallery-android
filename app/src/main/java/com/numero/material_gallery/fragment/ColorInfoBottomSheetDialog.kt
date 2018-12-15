package com.numero.material_gallery.fragment

import android.app.Dialog
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.R

class ColorInfoBottomSheetDialog : BottomSheetDialogFragment() {

    override fun setupDialog(dialog: Dialog, style: Int) {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_fragment_color_info, null)
        dialog.setContentView(view)
    }

    fun showIfNeed(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) != null) {
            return
        }
        show(fragmentManager, TAG)
    }

    companion object {
        private const val TAG = "ColorInfoBottomSheetDialog"

        fun newInstance(): ColorInfoBottomSheetDialog = ColorInfoBottomSheetDialog()
    }
}