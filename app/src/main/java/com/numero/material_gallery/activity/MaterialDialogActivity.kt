package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_material_dialog.*
import org.koin.android.ext.android.inject

class MaterialDialogActivity : AppCompatActivity(R.layout.activity_material_dialog) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        showDialogButton.setOnClickListener {
            showMaterialDialog()
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

    private fun showMaterialDialog() {
        MaterialAlertDialogBuilder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .show()
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MaterialDialogActivity::class.java)
    }
}