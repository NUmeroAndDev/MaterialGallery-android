package com.numero.material_gallery.components.appbar.bottom

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentBottomAppBarBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applyFloatingActionButtonEdgeTreatment
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class BottomAppBarFragment : MaterialContainerTransformFragment(R.layout.fragment_bottom_app_bar) {

    private val binding by viewBinding { FragmentBottomAppBarBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked FAB", Toast.LENGTH_SHORT).show()
        }

        binding.fabPositionRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.attachedCenterRadioButton -> {
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                }
                R.id.attachedEndRadioButton -> {
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                }
            }
        }

        binding.fabAnimationRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.scaleRadioButton -> {
                    binding.bottomAppBar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SCALE
                }
                R.id.slideRadioButton -> {
                    binding.bottomAppBar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE
                }
            }
        }

        binding.fabVisiblyRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.showRadioButton -> binding.fab.show()
                R.id.hideRadioButton -> binding.fab.hide()
            }
        }

        binding.hideOnScrollSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.bottomAppBar.hideOnScroll = isChecked
            if (isChecked.not()) {
                binding.bottomAppBar.performShow()
            }
        }

        binding.bottomAppBar.replaceMenu(R.menu.bottom_app_bar)
        binding.bottomAppBar.applyFloatingActionButtonEdgeTreatment(binding.fab)

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
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