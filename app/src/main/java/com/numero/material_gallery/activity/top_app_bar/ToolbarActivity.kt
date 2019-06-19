package com.numero.material_gallery.activity.top_app_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : AppCompatActivity(R.layout.activity_toolbar) {

    override fun onCreate(savedInstanceState: Bundle?) {
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
                ThemeInfoBottomSheetDialog.newInstance().showIfNeed(supportFragmentManager)
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        val toolbarList = listOf(primaryToolbar, surfaceToolbar)
        toolbarList.forEach {
            it.inflateMenu(R.menu.menu_action_bar)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ToolbarActivity::class.java)
    }

}