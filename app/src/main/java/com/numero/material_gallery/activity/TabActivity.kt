package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_tab.*
import org.koin.android.ext.android.inject

class TabActivity : AppCompatActivity(R.layout.activity_tab) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setupTab()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_common, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                ThemeInfoBottomSheetDialog.newInstance().showIfNeed(supportFragmentManager)
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTab() {
        withBadgeTabLayout.getTabAt(0)?.apply {
            orCreateBadge
        }
        withBadgeTabLayout.getTabAt(1)?.apply {
            orCreateBadge.apply {
                number = 10
            }
        }
        withBadgeTabLayout.getTabAt(2)?.apply {
            orCreateBadge.apply {
                number = 1000
            }
        }
        withBadgeTabLayout.getTabAt(3)?.apply {
            orCreateBadge.apply {
                maxCharacterCount = 5
                number = 5000
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TabActivity::class.java)
    }
}