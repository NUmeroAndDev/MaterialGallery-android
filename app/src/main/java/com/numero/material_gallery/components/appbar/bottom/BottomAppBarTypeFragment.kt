package com.numero.material_gallery.components.appbar.bottom

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import com.numero.material_gallery.view.ListItemAdapter
import kotlinx.android.synthetic.main.fragment_bottom_app_bar_type.*
import org.koin.android.ext.android.inject

class BottomAppBarTypeFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_bottom_app_bar_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        bottomAppBarTypeRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = ListItemAdapter(BottomAppBarType.values().toList()).apply {
                setOnItemClickListener {
                    selectedType(it)
                }
            }
        }
    }

    private fun selectedType(type: BottomAppBarType) {
        val intent = when (type) {
            BottomAppBarType.BOTTOM_APP_BAR -> BottomAppBarActivity.createIntent(requireContext())
            BottomAppBarType.HIDE_ON_SCROLL -> HideOnScrollActivity.createIntent(requireContext())
        }
        startActivity(intent)
    }
}