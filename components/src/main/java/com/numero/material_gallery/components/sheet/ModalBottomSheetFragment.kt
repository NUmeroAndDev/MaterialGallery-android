package com.numero.material_gallery.components.sheet

import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentModalBottomSheetBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding

class ModalBottomSheetFragment : MaterialContainerTransformFragment(R.layout.fragment_modal_bottom_sheet) {

    private val binding by viewBinding { FragmentModalBottomSheetBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showBottomSheetButton.setOnClickListener {
            BottomSheetModalFragment.newInstance().showIfNeeded(childFragmentManager)
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