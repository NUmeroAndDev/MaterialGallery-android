package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.numero.material_gallery.R
import kotlinx.android.synthetic.main.activity_fab.*

class FabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        fabSizeRadioGroup.setOnCheckedChangeListener { _, id ->
            fab.size = when (id) {
                R.id.fabSizeMinRadioButton -> FloatingActionButton.SIZE_MINI
                else -> FloatingActionButton.SIZE_NORMAL
            }
        }

        fab.setOnClickListener {
            Toast.makeText(this@FabActivity, "Clicked FAB", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, FabActivity::class.java)
    }
}