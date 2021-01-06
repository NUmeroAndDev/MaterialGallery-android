package com.numero.material_gallery.components.textfield

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import com.numero.material_gallery.databinding.FragmentTextFieldBinding

class TextFieldFragment : MaterialContainerTransformFragment(R.layout.fragment_text_field) {

    private var _binding: FragmentTextFieldBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTextFieldBinding.bind(view)

        binding.filledErrorTextInputLayout.error = "Error"
        binding.outlineErrorTextInputLayout.error = "Error"

        binding.customIconFilledTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }
        binding.customIconOutlineTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }

        val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        )
        binding.filledAutoCompleteTextView.setAdapter(adapter)
        binding.outlineAutoCompleteTextView.setAdapter(adapter)

        binding.scrollView.applySystemWindowInsetsPadding(applyBottom = true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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