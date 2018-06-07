package com.numero.material_gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.numero.material_gallery.activity.BottomSheetModalActivity
import com.numero.material_gallery.activity.MaterialButtonActivity
import com.numero.material_gallery.activity.FabActivity
import com.numero.material_gallery.model.DesignComponent
import com.numero.material_gallery.view.ComponentAdapter
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
            adapter = ComponentAdapter().apply {
                setOnItemClickListener {
                    selectedComponent(it)
                }
            }
        }
    }

    private fun selectedComponent(component: DesignComponent) {
        if (component.isEnable.not()) {
            return
        }
        when (component) {
            DesignComponent.BACKDROP -> {
            }
            DesignComponent.BOTTOM_APP_BAR -> {
            }
            DesignComponent.BOTTOM_NAVIGATION -> {
            }
            DesignComponent.BOTTOM_SHEET_MODAL -> {
                startActivity(BottomSheetModalActivity.createIntent(this))
            }
            DesignComponent.BOTTOM_SHEET_PERSISTENT -> {
            }
            DesignComponent.MATERIAL_BUTTON -> {
                startActivity(MaterialButtonActivity.createIntent(this))
            }
            DesignComponent.FAB -> {
                startActivity(FabActivity.createIntent(this))
            }
            DesignComponent.CARD -> {
            }
            DesignComponent.CHIPS -> {
            }
            DesignComponent.NAVIGATION_DRAWER -> {
            }
            DesignComponent.SNACKBAR -> {
            }
            DesignComponent.TAB -> {
            }
            DesignComponent.TEXT_FIELDS -> {
            }
        }
    }
}
