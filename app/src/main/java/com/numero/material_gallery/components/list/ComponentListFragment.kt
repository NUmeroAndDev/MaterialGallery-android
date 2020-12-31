package com.numero.material_gallery.components.list

import android.os.Bundle
import android.view.*
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult
import com.numero.material_gallery.R
import com.numero.material_gallery.components.DesignComponent
import com.numero.material_gallery.core.applySystemWindowInsetsPadding
import com.numero.material_gallery.databinding.FragmentComponentListBinding

class ComponentListFragment : Fragment(R.layout.fragment_component_list) {

    private lateinit var appUpdateManager: AppUpdateManager

    private var _binding: FragmentComponentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        appUpdateManager = AppUpdateManagerFactory.create(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentComponentListBinding.bind(view)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        ViewGroupCompat.setTransitionGroup(binding.rootLayout, true)

        initViews()
        binding.componentRecyclerView.applySystemWindowInsetsPadding(applyBottom = true)
    }

    override fun onResume() {
        super.onResume()
        checkUpdate()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                showSettingsScreen()
                true
            }
            else -> false
        }
    }

    private fun checkUpdate() {
        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            doUpdate(appUpdateInfo)
                        }
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            Snackbar.make(binding.rootLayout, R.string.update_message, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.update_button) {
                                        doUpdate(appUpdateInfo)
                                    }
                                    .show()
                        }
                    }
                }
    }

    private fun doUpdate(info: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(info, AppUpdateType.IMMEDIATE, this, UPDATE_REQUEST_CODE)
    }

    private fun showSettingsScreen() {
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)

        findNavController().navigate(R.id.action_ComponentList_to_Settings)
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

    companion object {
        private const val UPDATE_REQUEST_CODE = 1
    }
}
