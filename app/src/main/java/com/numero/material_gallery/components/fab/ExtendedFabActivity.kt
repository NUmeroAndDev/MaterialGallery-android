package com.numero.material_gallery.components.fab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_extended_fab.*
import org.koin.android.ext.android.inject

class ExtendedFabActivity : AppCompatActivity(R.layout.activity_extended_fab) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        fabStyleRadioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fabStyleExtendRadioButton -> extendedFab.extend()
                else -> extendedFab.shrink()
            }
        }

        extendedFab.setOnClickListener {
            Snackbar.make(rootLayout, "Clicked FAB", Snackbar.LENGTH_SHORT).setAnchorView(it).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_common, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                ThemeInfoBottomSheetDialog.newInstance().showIfNeeded(supportFragmentManager)
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ExtendedFabActivity::class.java)
    }
}