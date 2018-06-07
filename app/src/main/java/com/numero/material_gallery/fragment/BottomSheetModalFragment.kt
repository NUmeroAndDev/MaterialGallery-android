package com.numero.material_gallery.fragment

import android.app.Dialog
import android.view.View
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.R

class BottomSheetModalFragment : BottomSheetDialogFragment() {

    override fun setupDialog(dialog: Dialog?, style: Int) {
        val view = View.inflate(context, R.layout.fragment_bottom_sheet_modal, null)
        dialog?.setContentView(view)
    }

    fun show(manager: FragmentManager?) {
        show(manager, TAG)
    }

    companion object {
        private const val TAG: String = "BottomSheetModalFragment"

        fun newInstance(): BottomSheetModalFragment = BottomSheetModalFragment()
    }
}