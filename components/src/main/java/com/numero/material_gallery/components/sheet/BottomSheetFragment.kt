package com.numero.material_gallery.components.sheet

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentBottomSheetBinding
import com.numero.material_gallery.core.delegate.viewBinding

class BottomSheetFragment : ComponentFragment(R.layout.fragment_bottom_sheet) {

    private val binding by viewBinding { FragmentBottomSheetBinding.bind(it) }

    private lateinit var behavior: BottomSheetBehavior<LinearLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showBottomSheetButton.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.draggableSwitch.setOnCheckedChangeListener { _, isChecked ->
            behavior.isDraggable = isChecked
        }

        behavior = BottomSheetBehavior.from(binding.bottomSheetLayout).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(p0: View, p1: Float) = Unit
                override fun onStateChanged(view: View, state: Int) {
                    binding.showBottomSheetButton.isEnabled =
                        state == BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }
    }
}