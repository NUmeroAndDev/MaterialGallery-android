package com.numero.material_gallery.components.list

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult
import com.numero.material_gallery.R
import com.numero.material_gallery.components.DesignComponent
import com.numero.material_gallery.components.appbar.top.TopAppBarTypeActivity
import com.numero.material_gallery.view.ListItemAdapter
import kotlinx.android.synthetic.main.fragment_component_list.*

class ComponentListFragment : Fragment(R.layout.fragment_component_list) {

    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUpdateManager = AppUpdateManagerFactory.create(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insetsCompat ->
            componentRecyclerView.updatePadding(
                    left = 0,
                    top = 0,
                    right = 0,
                    bottom = insetsCompat.systemWindowInsetBottom
            )
            insetsCompat
        }
    }

    override fun onResume() {
        super.onResume()
        checkUpdate()
    }

    private fun checkUpdate() {
        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            doUpdate(appUpdateInfo)
                        }
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            Snackbar.make(rootLayout, R.string.update_message, Snackbar.LENGTH_LONG)
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
        findNavController().navigate(R.id.action_ComponentList_to_Settings)
    }

    private fun initViews() {
        toolbar.apply {
            inflateMenu(R.menu.menu_main)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_settings -> {
                        showSettingsScreen()
                        true
                    }
                    else -> false
                }
            }
        }
        componentRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = ListItemAdapter(DesignComponent.values().toList()).apply {
                setOnItemClickListener {
                    selectedComponent(it)
                }
            }
        }
    }

    private fun selectedComponent(component: DesignComponent) {
        when (component) {
            DesignComponent.BOTTOM_APP_BAR -> {
                findNavController().navigate(R.id.action_ComponentList_to_BottomAppBarType)
            }
            DesignComponent.BOTTOM_NAVIGATION -> {
                findNavController().navigate(R.id.action_ComponentList_to_BottomNavigation)
            }
            DesignComponent.MODAL_BOTTOM_SHEET -> {
                findNavController().navigate(R.id.action_ComponentList_to_ModalBottomSheet)
            }
            DesignComponent.BOTTOM_SHEET -> {
                findNavController().navigate(R.id.action_ComponentList_to_BottomSheet)
            }
            DesignComponent.CHECKBOX -> {
                findNavController().navigate(R.id.action_ComponentList_to_Checkbox)
            }
            DesignComponent.CHIPS -> {
                findNavController().navigate(R.id.action_ComponentList_to_Chip)
            }
            DesignComponent.DATE_PICKER -> {
                findNavController().navigate(R.id.action_ComponentList_to_DatePicker)
            }
            DesignComponent.MATERIAL_BUTTON -> {
                findNavController().navigate(R.id.action_ComponentList_to_MaterialButton)
            }
            DesignComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> {
                findNavController().navigate(R.id.action_ComponentList_to_MaterialButtonToggleGroup)
            }
            DesignComponent.EXTENDED_FAB -> {
                findNavController().navigate(R.id.action_ComponentList_to_ExtendedFab)
            }
            DesignComponent.FAB -> {
                findNavController().navigate(R.id.action_ComponentList_to_Fab)
            }
            DesignComponent.IMAGE_VIEW -> {
                findNavController().navigate(R.id.action_ComponentList_to_ShapeableImage)
            }
            DesignComponent.MATERIAL_CARD -> {
                findNavController().navigate(R.id.action_ComponentList_to_MaterialCard)
            }
            DesignComponent.MATERIAL_DIALOG -> {
                findNavController().navigate(R.id.action_ComponentList_to_MaterialDialog)
            }
            DesignComponent.NAVIGATION_DRAWER -> {
                findNavController().navigate(R.id.action_ComponentList_to_NavigationDrawer)
            }
            DesignComponent.PROGRESS_INDICATOR -> {
                findNavController().navigate(R.id.action_ComponentList_to_ProgressIndicator)
            }
            DesignComponent.RADIO_BUTTON -> {
                findNavController().navigate(R.id.action_ComponentList_to_RadioButton)
            }
            DesignComponent.SLIDER -> {
                findNavController().navigate(R.id.action_ComponentList_to_Slider)
            }
            DesignComponent.SNACKBAR -> {
                findNavController().navigate(R.id.action_ComponentList_to_Snackbar)
            }
            DesignComponent.SWITCH -> {
                findNavController().navigate(R.id.action_ComponentList_to_Switch)
            }
            DesignComponent.TAB -> {
                findNavController().navigate(R.id.action_ComponentList_to_Tab)
            }
            DesignComponent.TEXT_FIELDS -> {
                findNavController().navigate(R.id.action_ComponentList_to_TextField)
            }
            DesignComponent.TOP_APP_BAR -> {
                startActivity(TopAppBarTypeActivity.createIntent(requireContext()))
            }
        }
    }

    companion object {
        private const val UPDATE_REQUEST_CODE = 1
    }
}
