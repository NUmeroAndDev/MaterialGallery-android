package com.numero.material_gallery.activity.top_app_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.numero.material_gallery.R
import com.numero.material_gallery.model.TopAppBarType
import com.numero.material_gallery.view.ListItemAdapter
import kotlinx.android.synthetic.main.activity_top_app_bar_type.*

@ContentView(R.layout.activity_top_app_bar_type)
class TopAppBarTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        toolbarTypeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TopAppBarTypeActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@TopAppBarTypeActivity, DividerItemDecoration.VERTICAL))
            adapter = ListItemAdapter(TopAppBarType.values().toList()).apply {
                setOnItemClickListener {
                    selectedToolbarType(it)
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

    private fun selectedToolbarType(topAppBarType: TopAppBarType) {
        val intent = when (topAppBarType) {
            TopAppBarType.ACTION_BAR -> ActionBarActivity.createIntent(this)
            TopAppBarType.LIFT_ON_SCROLL -> LiftOnScrollActivity.createIntent(this)
            TopAppBarType.COLLAPSING -> CollapsingActivity.createIntent(this)
        }
        startActivity(intent)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TopAppBarTypeActivity::class.java)
    }
}