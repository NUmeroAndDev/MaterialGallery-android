package com.numero.material_gallery.components.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_modal_bottom_sheet.*

class ModalBottomSheetFragment : MaterialContainerTransformFragment(R.layout.fragment_modal_bottom_sheet) {

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

        showBottomSheetButton.setOnClickListener {
            BottomSheetModalFragment.newInstance().showIfNeeded(childFragmentManager)
        }
    }

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
}