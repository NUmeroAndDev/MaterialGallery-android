package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.R
import kotlinx.android.synthetic.main.activity_bottom_navgation.*

class BottomNavigationActivity : AppCompatActivity() {

    private var style: BottomNavigationStyle = BottomNavigationStyle.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navgation)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        styleRadioGroup.setOnCheckedChangeListener { _, id ->
            style = when (id) {
                R.id.styleColoredRadioButton -> BottomNavigationStyle.COLORED
                else -> BottomNavigationStyle.DEFAULT
            }
            switchBottomNavigation(style)
        }

        switchBottomNavigation(style)
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

    private fun switchBottomNavigation(style: BottomNavigationStyle) {
        when (style) {
            BottomNavigationStyle.DEFAULT -> {
                bottomNavigation.visibility = View.VISIBLE
                coloredBottomNavigation.visibility = View.GONE
            }
            BottomNavigationStyle.COLORED -> {
                bottomNavigation.visibility = View.GONE
                coloredBottomNavigation.visibility = View.VISIBLE
            }
        }
    }

    enum class BottomNavigationStyle {
        DEFAULT,
        COLORED
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, BottomNavigationActivity::class.java)
    }
}