package com.numero.material_gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.numero.material_gallery.activity.*
import com.numero.material_gallery.activity.bottom_app_bar.BottomAppBarTypeActivity
import com.numero.material_gallery.activity.top_app_bar.TopAppBarTypeActivity
import com.numero.material_gallery.model.DesignComponent
import com.numero.material_gallery.view.ListItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        appUpdateManager = AppUpdateManagerFactory.create(this)

        initViews()
        checkUpdate()
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                        doUpdate(appUpdateInfo)
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                showSettingsScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                Snackbar.make(rootLayout, "There is an update.", Snackbar.LENGTH_LONG).setAction("Update") {
                    doUpdate(appUpdateInfo)
                }.show()
            }
        }
    }

    private fun doUpdate(info: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(info, AppUpdateType.IMMEDIATE, this, UPDATE_REQUEST_CODE)
    }

    private fun showSettingsScreen() {
        startActivity(SettingsActivity.createIntent(this))
    }

    private fun initViews() {
        componentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
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
                startActivity(BottomAppBarTypeActivity.createIntent(this))
            }
            DesignComponent.BOTTOM_NAVIGATION -> {
                startActivity(BottomNavigationActivity.createIntent(this))
            }
            DesignComponent.MODAL_BOTTOM_SHEET -> {
                startActivity(ModalBottomSheetActivity.createIntent(this))
            }
            DesignComponent.BOTTOM_SHEET -> {
                startActivity(BottomSheetActivity.createIntent(this))
            }
            DesignComponent.CHECKBOX -> {
                startActivity(CheckboxActivity.createIntent(this))
            }
            DesignComponent.CHIPS -> {
                startActivity(ChipActivity.createIntent(this))
            }
            DesignComponent.MATERIAL_BUTTON -> {
                startActivity(MaterialButtonActivity.createIntent(this))
            }
            DesignComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> {
                startActivity(MaterialButtonToggleGroupActivity.createIntent(this))
            }
            DesignComponent.EXTENDED_FAB -> {
                startActivity(ExtendedFabActivity.createIntent(this))
            }
            DesignComponent.FAB -> {
                startActivity(FabActivity.createIntent(this))
            }
            DesignComponent.MATERIAL_CARD -> {
                startActivity(MaterialCardActivity.createIntent(this))
            }
            DesignComponent.MATERIAL_DIALOG -> {
                startActivity(MaterialDialogActivity.createIntent(this))
            }
            DesignComponent.NAVIGATION_DRAWER -> {
                startActivity(NavigationDrawerActivity.createIntent(this))
            }
            DesignComponent.PROGRESS_BAR -> {
                startActivity(ProgressBarActivity.createIntent(this))
            }
            DesignComponent.RADIO_BUTTON -> {
                startActivity(RadioButtonActivity.createIntent(this))
            }
            DesignComponent.SNACKBAR -> {
                startActivity(SnackbarActivity.createIntent(this))
            }
            DesignComponent.SWITCH -> {
                startActivity(SwitchActivity.createIntent(this))
            }
            DesignComponent.TAB -> {
                startActivity(TabActivity.createIntent(this))
            }
            DesignComponent.TEXT_FIELDS -> {
                startActivity(TextFieldActivity.createIntent(this))
            }
            DesignComponent.TOP_APP_BAR -> {
                startActivity(TopAppBarTypeActivity.createIntent(this))
            }
        }
    }

    companion object {
        private const val UPDATE_REQUEST_CODE = 1
    }
}
