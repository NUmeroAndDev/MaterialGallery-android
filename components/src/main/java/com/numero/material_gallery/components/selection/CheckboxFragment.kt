package com.numero.material_gallery.components.selection

import android.os.Bundle
import android.view.View
import com.google.android.material.checkbox.MaterialCheckBox
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentCheckboxBinding
import com.numero.material_gallery.core.delegate.viewBinding

class CheckboxFragment : ComponentFragment(R.layout.fragment_checkbox) {

    private val binding by viewBinding { FragmentCheckboxBinding.bind(it) }

    private var isUpdatingChildren = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val childrenCheckBoxes = listOf(
            binding.child1Checkbox,
            binding.child2Checkbox,
            binding.child3Checkbox,
            binding.child4Checkbox,
        )
        val parentOnCheckedStateChangedListener = { checkBox: MaterialCheckBox, state: Int ->
            val isChecked = checkBox.isChecked
            if (state != MaterialCheckBox.STATE_INDETERMINATE) {
                isUpdatingChildren = true
                childrenCheckBoxes.forEach {
                    it.isChecked = isChecked
                }
                isUpdatingChildren = false
            }
        }
        binding.parentCheckbox.addOnCheckedStateChangedListener(parentOnCheckedStateChangedListener)
        childrenCheckBoxes.forEach {
            it.addOnCheckedStateChangedListener { _, _ ->
                if (!isUpdatingChildren) {
                    setParentState(
                        binding.parentCheckbox,
                        childrenCheckBoxes,
                        parentOnCheckedStateChangedListener
                    )
                }
            }
        }
        binding.child1Checkbox.isChecked = true
    }

    private fun setParentState(
        checkBoxParent: MaterialCheckBox,
        childrenCheckBoxes: List<MaterialCheckBox>,
        parentOnCheckedStateChangedListener: MaterialCheckBox.OnCheckedStateChangedListener
    ) {
        val checkedCount = childrenCheckBoxes.count { it.isChecked }
        val allChecked = checkedCount == childrenCheckBoxes.size
        val noneChecked = checkedCount == 0
        checkBoxParent.removeOnCheckedStateChangedListener(parentOnCheckedStateChangedListener)
        if (allChecked) {
            checkBoxParent.isChecked = true
        } else if (noneChecked) {
            checkBoxParent.isChecked = false
        } else {
            checkBoxParent.checkedState = MaterialCheckBox.STATE_INDETERMINATE
        }
        checkBoxParent.addOnCheckedStateChangedListener(parentOnCheckedStateChangedListener)
    }
}