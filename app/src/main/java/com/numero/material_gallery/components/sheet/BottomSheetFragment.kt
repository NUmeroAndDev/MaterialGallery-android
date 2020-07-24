package com.numero.material_gallery.components.sheet

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheetFragment : MaterialContainerTransformFragment(R.layout.fragment_bottom_sheet) {

    private lateinit var behavior: BottomSheetBehavior<LinearLayout>

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
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        draggableSwitch.setOnCheckedChangeListener { _, isChecked ->
            behavior.isDraggable = isChecked
        }

        behavior = BottomSheetBehavior.from(bottomSheetLayout).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(p0: View, p1: Float) {
                }

                override fun onStateChanged(view: View, state: Int) {
                    showBottomSheetButton.isEnabled = state == BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }
    }
}