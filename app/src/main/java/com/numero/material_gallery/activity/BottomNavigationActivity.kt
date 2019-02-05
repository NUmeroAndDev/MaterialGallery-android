package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.numero.material_gallery.R
import com.numero.material_gallery.extension.setCheckedItem
import com.numero.material_gallery.extension.setVisibleItem
import com.numero.material_gallery.extension.visibleItemCount
import com.numero.material_gallery.fragment.ColorInfoBottomSheetDialog
import com.numero.material_gallery.repository.IConfigRepository
import kotlinx.android.synthetic.main.activity_bottom_navgation.*
import org.koin.android.ext.android.inject

@ContentView(R.layout.activity_bottom_navgation)
class BottomNavigationActivity : AppCompatActivity() {

    private val configRepository by inject<IConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_common, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                ColorInfoBottomSheetDialog.newInstance().showIfNeed(supportFragmentManager)
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        val listener = BottomNavigationView.OnNavigationItemSelectedListener {
            bottomNavigation.setCheckedItem(it.itemId, true)
            coloredBottomNavigation.setCheckedItem(it.itemId, true)
            false
        }
        bottomNavigation.setOnNavigationItemSelectedListener(listener)
        coloredBottomNavigation.setOnNavigationItemSelectedListener(listener)

        removeItemButton.setOnClickListener {
            updateBottomNavigationItemCount(MenuItemAction.REMOVE)
        }
        addItemButton.setOnClickListener {
            updateBottomNavigationItemCount(MenuItemAction.ADD)
        }
    }

    private fun updateBottomNavigationItemCount(action: MenuItemAction) {
        val currentItemCount = bottomNavigation.visibleItemCount
        when (action) {
            MenuItemAction.ADD -> {
                bottomNavigation.setVisibleItem(currentItemCount, true)
                coloredBottomNavigation.setVisibleItem(currentItemCount, true)
                removeItemButton.isEnabled = true
                addItemButton.isEnabled = currentItemCount + 1 < MAX_ITEM_COUNT
            }
            MenuItemAction.REMOVE -> {
                bottomNavigation.setVisibleItem(currentItemCount - 1, false)
                coloredBottomNavigation.setVisibleItem(currentItemCount - 1, false)
                removeItemButton.isEnabled = currentItemCount - 1 > MIN_ITEM_COUNT
                addItemButton.isEnabled = true
            }
        }
    }

    private enum class MenuItemAction {
        ADD, REMOVE
    }

    companion object {

        private const val MIN_ITEM_COUNT = 3
        private const val MAX_ITEM_COUNT = 5

        fun createIntent(context: Context): Intent = Intent(context, BottomNavigationActivity::class.java)
    }
}