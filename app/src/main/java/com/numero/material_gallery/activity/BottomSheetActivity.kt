package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ColorInfoBottomSheetDialog
import com.numero.material_gallery.repository.IConfigRepository
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import org.koin.android.ext.android.inject

class BottomSheetActivity : AppCompatActivity(R.layout.activity_bottom_sheet) {

    private val configRepository by inject<IConfigRepository>()

    private lateinit var behavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        showBottomSheetButton.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        fab.setOnClickListener {
            Toast.makeText(this@BottomSheetActivity, "Clicked FAB", Toast.LENGTH_SHORT).show()
        }

        behavior = BottomSheetBehavior.from(bottomSheetLayout).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
            setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(p0: View, p1: Float) {
                }

                override fun onStateChanged(view: View, state: Int) {
                    showBottomSheetButton.isEnabled = when (state) {
                        BottomSheetBehavior.STATE_EXPANDED -> false
                        else -> true
                    }
                }
            })
        }
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

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, BottomSheetActivity::class.java)
    }
}