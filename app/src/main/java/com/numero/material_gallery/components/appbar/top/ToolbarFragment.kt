package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.synthetic.main.fragment_toolbar.*

class ToolbarFragment : MaterialContainerTransformFragment(R.layout.fragment_toolbar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        initViews()
    }

    private fun initViews() {
        val toolbarList = listOf(primaryToolbar, surfaceToolbar, primarySurfaceToolbar)
        toolbarList.forEach {
            it.inflateMenu(R.menu.menu_action_bar)
        }
    }
}