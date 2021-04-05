package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.databinding.FragmentCollapsingBinding
import dev.chrisbanes.insetter.applyInsetter

class CollapsingFragment : MaterialContainerTransformFragment(R.layout.fragment_collapsing) {

    private var _binding: FragmentCollapsingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCollapsingBinding.bind(view)

        binding.toolbar.apply {
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

        binding.scrollView.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}