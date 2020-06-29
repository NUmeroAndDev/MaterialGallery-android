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
import com.numero.material_gallery.components.appbar.bottom.BottomAppBarTypeActivity
import com.numero.material_gallery.components.appbar.top.TopAppBarTypeActivity
import com.numero.material_gallery.components.bottomnavigation.BottomNavigationActivity
import com.numero.material_gallery.components.button.MaterialButtonActivity
import com.numero.material_gallery.components.button.MaterialButtonToggleGroupActivity
import com.numero.material_gallery.components.card.MaterialCardActivity
import com.numero.material_gallery.components.chip.ChipActivity
import com.numero.material_gallery.components.dialog.MaterialDialogActivity
import com.numero.material_gallery.components.fab.ExtendedFabActivity
import com.numero.material_gallery.components.fab.FabActivity
import com.numero.material_gallery.components.image.ShapeableImageViewActivity
import com.numero.material_gallery.components.navigationdrawer.NavigationDrawerActivity
import com.numero.material_gallery.components.picker.DatePickerActivity
import com.numero.material_gallery.components.selection.CheckboxActivity
import com.numero.material_gallery.components.selection.RadioButtonActivity
import com.numero.material_gallery.components.selection.SwitchActivity
import com.numero.material_gallery.components.sheet.BottomSheetActivity
import com.numero.material_gallery.components.sheet.ModalBottomSheetActivity
import com.numero.material_gallery.components.slider.SliderActivity
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
                startActivity(BottomAppBarTypeActivity.createIntent(requireContext()))
            }
            DesignComponent.BOTTOM_NAVIGATION -> {
                startActivity(BottomNavigationActivity.createIntent(requireContext()))
            }
            DesignComponent.MODAL_BOTTOM_SHEET -> {
                startActivity(ModalBottomSheetActivity.createIntent(requireContext()))
            }
            DesignComponent.BOTTOM_SHEET -> {
                startActivity(BottomSheetActivity.createIntent(requireContext()))
            }
            DesignComponent.CHECKBOX -> {
                startActivity(CheckboxActivity.createIntent(requireContext()))
            }
            DesignComponent.CHIPS -> {
                startActivity(ChipActivity.createIntent(requireContext()))
            }
            DesignComponent.DATE_PICKER -> {
                startActivity(DatePickerActivity.createIntent(requireContext()))
            }
            DesignComponent.MATERIAL_BUTTON -> {
                startActivity(MaterialButtonActivity.createIntent(requireContext()))
            }
            DesignComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> {
                startActivity(MaterialButtonToggleGroupActivity.createIntent(requireContext()))
            }
            DesignComponent.EXTENDED_FAB -> {
                startActivity(ExtendedFabActivity.createIntent(requireContext()))
            }
            DesignComponent.FAB -> {
                startActivity(FabActivity.createIntent(requireContext()))
            }
            DesignComponent.IMAGE_VIEW -> {
                startActivity(ShapeableImageViewActivity.createIntent(requireContext()))
            }
            DesignComponent.MATERIAL_CARD -> {
                startActivity(MaterialCardActivity.createIntent(requireContext()))
            }
            DesignComponent.MATERIAL_DIALOG -> {
                startActivity(MaterialDialogActivity.createIntent(requireContext()))
            }
            DesignComponent.NAVIGATION_DRAWER -> {
                startActivity(NavigationDrawerActivity.createIntent(requireContext()))
            }
            DesignComponent.PROGRESS_INDICATOR -> {
                findNavController().navigate(R.id.action_ComponentList_to_ProgressIndicator)
            }
            DesignComponent.RADIO_BUTTON -> {
                startActivity(RadioButtonActivity.createIntent(requireContext()))
            }
            DesignComponent.SLIDER -> {
                startActivity(SliderActivity.createIntent(requireContext()))
            }
            DesignComponent.SNACKBAR -> {
                findNavController().navigate(R.id.action_ComponentList_to_Snackbar)
            }
            DesignComponent.SWITCH -> {
                startActivity(SwitchActivity.createIntent(requireContext()))
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
