package com.numero.material_gallery.components.button

import android.os.Bundle
import android.view.View
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentMaterialButtonToggleGroupBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class MaterialButtonToggleGroupFragment :
    ComponentFragment(R.layout.fragment_material_button_toggle_group) {

    private val binding by viewBinding { FragmentMaterialButtonToggleGroupBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}