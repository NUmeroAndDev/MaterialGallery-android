package com.numero.material_gallery.components

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
import com.numero.material_gallery.components.databinding.FragmentComponentListBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class ComponentListFragment : Fragment(R.layout.fragment_component_list) {

    private val binding by viewBinding<FragmentComponentListBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            adapter = ComponentListAdapter().apply {
                setOnItemClickListener { view, component ->
                    selectedComponent(view, component)
                }
            }
        }
    }

    private fun selectedComponent(view: View, component: MaterialComponent) {
        exitTransition = Hold()
        reenterTransition = null

        val extras = FragmentNavigatorExtras(view to view.transitionName)
        findNavController().navigate(component.navigationId, null, null, extras)
    }

    private val MaterialComponent.navigationId: Int
        get() = when (this) {
            MaterialComponent.BOTTOM_NAVIGATION -> R.id.action_ComponentList_to_BottomNavigation
            MaterialComponent.BOTTOM_APP_BAR -> R.id.action_ComponentList_to_BottomAppBar
            MaterialComponent.BOTTOM_SHEET -> R.id.action_ComponentList_to_BottomSheet
            MaterialComponent.CHECKBOX -> R.id.action_ComponentList_to_Checkbox
            MaterialComponent.CHIPS -> R.id.action_ComponentList_to_Chip
            MaterialComponent.DATE_PICKER -> R.id.action_ComponentList_to_DatePicker
            MaterialComponent.EXTENDED_FAB -> R.id.action_ComponentList_to_ExtendedFab
            MaterialComponent.FAB -> R.id.action_ComponentList_to_Fab
            MaterialComponent.IMAGE_VIEW -> R.id.action_ComponentList_to_ShapeableImage
            MaterialComponent.MATERIAL_BUTTON -> R.id.action_ComponentList_to_MaterialButton
            MaterialComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> R.id.action_ComponentList_to_MaterialButtonToggleGroup
            MaterialComponent.MATERIAL_CARD -> R.id.action_ComponentList_to_MaterialCard
            MaterialComponent.MATERIAL_DIALOG -> R.id.action_ComponentList_to_MaterialDialog
            MaterialComponent.MENU -> R.id.action_ComponentList_to_Menu
            MaterialComponent.MODAL_BOTTOM_SHEET -> R.id.action_ComponentList_to_ModalBottomSheet
            MaterialComponent.NAVIGATION_DRAWER -> R.id.action_ComponentList_to_NavigationDrawer
            MaterialComponent.NAVIGATION_RAIL -> R.id.action_ComponentList_to_NavigationRail
            MaterialComponent.PROGRESS_INDICATOR -> R.id.action_ComponentList_to_ProgressIndicator
            MaterialComponent.RADIO_BUTTON -> R.id.action_ComponentList_to_RadioButton
            MaterialComponent.SLIDER -> R.id.action_ComponentList_to_Slider
            MaterialComponent.SNACKBAR -> R.id.action_ComponentList_to_Snackbar
            MaterialComponent.SWITCH -> R.id.action_ComponentList_to_Switch
            MaterialComponent.TAB -> R.id.action_ComponentList_to_Tab
            MaterialComponent.TEXT_FIELDS -> R.id.action_ComponentList_to_TextField
            MaterialComponent.TIME_PICKER -> R.id.action_ComponentList_to_TimePicker
            MaterialComponent.TOP_APP_BAR -> R.id.action_ComponentList_to_TopAppBar
        }
}
