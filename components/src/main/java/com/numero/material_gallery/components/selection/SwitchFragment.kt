package com.numero.material_gallery.components.selection

import android.os.Bundle
import android.view.View
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentSwitchBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class SwitchFragment : ComponentFragment(R.layout.fragment_switch) {

    private val binding by viewBinding { FragmentSwitchBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}