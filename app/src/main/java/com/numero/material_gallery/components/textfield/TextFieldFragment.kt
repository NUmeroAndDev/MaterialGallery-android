package com.numero.material_gallery.components.textfield

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.fragment_text_field.*
import org.koin.android.ext.android.inject

class TextFieldFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_text_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.menu_common)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_info -> {
                        findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                        true
                    }
                    else -> false
                }
            }
        }

        filledErrorTextInputLayout.error = "Error"
        outlineErrorTextInputLayout.error = "Error"

        customIconFilledTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }
        customIconOutlineTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Clicked end icon", Toast.LENGTH_SHORT).show()
        }

        val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        )
        filledAutoCompleteTextView.setAdapter(adapter)
        outlineAutoCompleteTextView.setAdapter(adapter)
    }
}