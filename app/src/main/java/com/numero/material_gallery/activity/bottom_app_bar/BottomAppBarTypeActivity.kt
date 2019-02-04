package com.numero.material_gallery.activity.bottom_app_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.numero.material_gallery.R
import com.numero.material_gallery.model.BottomAppBarType
import com.numero.material_gallery.view.ListItemAdapter
import kotlinx.android.synthetic.main.activity_bottom_app_bar_type.*

@ContentView(R.layout.activity_bottom_app_bar_type)
class BottomAppBarTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        bottomAppBarTypeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BottomAppBarTypeActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@BottomAppBarTypeActivity, DividerItemDecoration.VERTICAL))
            adapter = ListItemAdapter(BottomAppBarType.values().toList()).apply {
                setOnItemClickListener {
                    selectedType(it)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun selectedType(type: BottomAppBarType) {
        val intent = when (type) {
            BottomAppBarType.BOTTOM_APP_BAR -> BottomAppBarActivity.createIntent(this)
            BottomAppBarType.HIDE_ON_SCROLL -> HideOnScrollActivity.createIntent(this)
        }
        startActivity(intent)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, BottomAppBarTypeActivity::class.java)
    }
}