package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.databinding.FragmentToolbarBinding

class ToolbarFragment : MaterialContainerTransformFragment(R.layout.fragment_toolbar) {

    private var _binding: FragmentToolbarBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentToolbarBinding.bind(view)

        initViews()
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

    private fun initViews() {
        val toolbarList = listOf(
            binding.primaryToolbar,
            binding.surfaceToolbar,
            binding.primarySurfaceToolbar
        )
        toolbarList.forEach {
            it.inflateMenu(R.menu.menu_action_bar)
        }
    }
}