package com.numero.material_gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.numero.material_gallery.activity.*
import com.numero.material_gallery.activity.bottom_app_bar.BottomAppBarTypeActivity
import com.numero.material_gallery.activity.top_app_bar.TopAppBarTypeActivity
import com.numero.material_gallery.model.DesignComponent
import com.numero.material_gallery.view.ListItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
            DesignComponent.MATERIAL_BUTTON -> {
                startActivity(MaterialButtonActivity.createIntent(this))
            }
            DesignComponent.FAB -> {
                startActivity(FabActivity.createIntent(this))
            }
            DesignComponent.MATERIAL_CARD -> {
                startActivity(MaterialCardActivity.createIntent(this))
            }
            DesignComponent.NAVIGATION_DRAWER -> {
                startActivity(NavigationDrawerActivity.createIntent(this))
            }
            DesignComponent.PROGRESS_BAR -> {
                startActivity(ProgressBarActivity.createIntent(this))
            }
            DesignComponent.SNACKBAR -> {
                startActivity(SnackbarActivity.createIntent(this))
            }
            DesignComponent.TEXT_FIELDS -> {
                startActivity(TextFieldActivity.createIntent(this))
            }
            DesignComponent.TOP_APP_BAR -> {
                startActivity(TopAppBarTypeActivity.createIntent(this))
            }
        }
    }
}
