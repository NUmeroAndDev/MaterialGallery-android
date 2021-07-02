package com.numero.material_gallery.components.sheet

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentBottomSheetBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.delegate.viewBinding

class BottomSheetFragment : MaterialContainerTransformFragment(R.layout.fragment_bottom_sheet) {

    private val binding by viewBinding { FragmentBottomSheetBinding.bind(it) }

    private lateinit var behavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
}