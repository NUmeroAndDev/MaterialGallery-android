package com.numero.material_gallery.components.textfield

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentTextFieldBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class TextFieldFragment : ComponentFragment(R.layout.fragment_text_field) {

    private val binding by viewBinding { FragmentTextFieldBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filledErrorTextInputLayout.error = "Error"
        binding.outlinedErrorTextInputLayout.error = "Error"

        binding.iconFilledTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }
        binding.iconOutlinedTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        )
        binding.filledAutoCompleteTextView.setAdapter(adapter)
        binding.outlinedAutoCompleteTextView.setAdapter(adapter)

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
    }
}