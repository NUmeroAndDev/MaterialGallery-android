package com.numero.material_gallery.components.dialog

import android.os.Bundle
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentMaterialDialogBinding
import com.numero.material_gallery.core.delegate.viewBinding

class MaterialDialogFragment : ComponentFragment(R.layout.fragment_material_dialog) {

    private val binding by viewBinding { FragmentMaterialDialogBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showDialogButton.setOnClickListener {
            showMaterialDialog()
        }
    }

    private fun showMaterialDialog() {
        MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .show()
    }
}