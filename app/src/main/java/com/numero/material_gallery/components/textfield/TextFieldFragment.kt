package com.numero.material_gallery.components.textfield

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.databinding.FragmentTextFieldBinding
import dev.chrisbanes.insetter.applyInsetter

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
        binding.outlinedErrorTextInputLayout.error = "Error"

        binding.customIconFilledTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }
        binding.customIconOutlinedTextInputLayout.setEndIconOnClickListener {
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