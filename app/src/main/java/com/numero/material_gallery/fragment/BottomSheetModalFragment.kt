package com.numero.material_gallery.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.R

class BottomSheetModalFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_modal, container, false)
    }

    fun showIfNeeded(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) != null) return
        showNow(fragmentManager, TAG)
    }

    companion object {
        private const val TAG: String = "BottomSheetModalFragment"

        fun newInstance(): BottomSheetModalFragment = BottomSheetModalFragment()
    }
}