package com.numero.material_gallery.components.appbar.top

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
import kotlinx.android.synthetic.main.fragment_top_app_bar_type.*
import org.koin.android.ext.android.inject

class TopAppBarTypeFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_top_app_bar_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        toolbarTypeRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = ListItemAdapter(TopAppBarType.values().toList()).apply {
                setOnItemClickListener {
                    selectedToolbarType(it)
                }
            }
        }
    }

    private fun selectedToolbarType(topAppBarType: TopAppBarType) {
        val intent = when (topAppBarType) {
            TopAppBarType.ACTION_BAR -> ActionBarActivity.createIntent(requireContext())
            TopAppBarType.TOOLBAR -> ToolbarActivity.createIntent(requireContext())
            TopAppBarType.LIFT_ON_SCROLL -> LiftOnScrollActivity.createIntent(requireContext())
            TopAppBarType.COLLAPSING -> CollapsingActivity.createIntent(requireContext())
        }
        startActivity(intent)
    }
}