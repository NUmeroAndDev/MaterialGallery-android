package com.numero.material_gallery.components.list

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialFadeThrough
import com.numero.material_gallery.R
import com.numero.material_gallery.components.DesignComponent
import com.numero.material_gallery.databinding.FragmentComponentListBinding
import dev.chrisbanes.insetter.applyInsetter

class ComponentListFragment : Fragment(R.layout.fragment_component_list) {

    private var _binding: FragmentComponentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentComponentListBinding.bind(view)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        exitTransition = MaterialFadeThrough()
        enterTransition = MaterialFadeThrough()
        ViewGroupCompat.setTransitionGroup(binding.rootLayout, true)

        initViews()
        binding.componentRecyclerView.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
    }

    private fun initViews() {
        binding.componentRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = ComponentListAdapter().apply {
                setOnItemClickListener { view, component ->
                    selectedComponent(view, component)
                }
            }
        }
    }

    private fun selectedComponent(view: View, component: DesignComponent) {
        exitTransition = Hold()
        reenterTransition = null

        val extras = FragmentNavigatorExtras(view to view.transitionName)
        findNavController().navigate(component.navigationId, null, null, extras)
    }

    private val DesignComponent.navigationId: Int
        get() = when (this) {
            DesignComponent.BOTTOM_NAVIGATION -> R.id.action_ComponentList_to_BottomNavigation
            DesignComponent.BOTTOM_APP_BAR -> R.id.action_ComponentList_to_BottomAppBar
            DesignComponent.BOTTOM_SHEET -> R.id.action_ComponentList_to_BottomSheet
            DesignComponent.CHECKBOX -> R.id.action_ComponentList_to_Checkbox
            DesignComponent.CHIPS -> R.id.action_ComponentList_to_Chip
            DesignComponent.DATE_PICKER -> R.id.action_ComponentList_to_DatePicker
            DesignComponent.EXTENDED_FAB -> R.id.action_ComponentList_to_ExtendedFab
            DesignComponent.FAB -> R.id.action_ComponentList_to_Fab
            DesignComponent.IMAGE_VIEW -> R.id.action_ComponentList_to_ShapeableImage
            DesignComponent.MATERIAL_BUTTON -> R.id.action_ComponentList_to_MaterialButton
            DesignComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> R.id.action_ComponentList_to_MaterialButtonToggleGroup
            DesignComponent.MATERIAL_CARD -> R.id.action_ComponentList_to_MaterialCard
            DesignComponent.MATERIAL_DIALOG -> R.id.action_ComponentList_to_MaterialDialog
            DesignComponent.MODAL_BOTTOM_SHEET -> R.id.action_ComponentList_to_ModalBottomSheet
            DesignComponent.NAVIGATION_DRAWER -> R.id.action_ComponentList_to_NavigationDrawer
            DesignComponent.PROGRESS_INDICATOR -> R.id.action_ComponentList_to_ProgressIndicator
            DesignComponent.RADIO_BUTTON -> R.id.action_ComponentList_to_RadioButton
            DesignComponent.SLIDER -> R.id.action_ComponentList_to_Slider
            DesignComponent.SNACKBAR -> R.id.action_ComponentList_to_Snackbar
            DesignComponent.SWITCH -> R.id.action_ComponentList_to_Switch
            DesignComponent.TAB -> R.id.action_ComponentList_to_Tab
            DesignComponent.TEXT_FIELDS -> R.id.action_ComponentList_to_TextField
            DesignComponent.TIME_PICKER -> R.id.action_ComponentList_to_TimePicker
            DesignComponent.TOP_APP_BAR -> R.id.action_ComponentList_to_TopAppBar
        }
}
