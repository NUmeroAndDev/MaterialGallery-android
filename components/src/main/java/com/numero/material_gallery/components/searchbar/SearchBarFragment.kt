package com.numero.material_gallery.components.searchbar

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.google.android.material.search.SearchView
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentSearchBarBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class SearchBarFragment : ComponentFragment(R.layout.fragment_search_bar) {

    private val binding by viewBinding { FragmentSearchBarBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }

        val onBackPressedCallback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                binding.searchView.hide()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            onBackPressedCallback
        )
        binding.searchView.addTransitionListener { _, _, newState ->
            onBackPressedCallback.isEnabled = newState == SearchView.TransitionState.SHOWN
        }
    }
}