package com.numero.material_gallery.activity.toolbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.numero.material_gallery.R
import com.numero.material_gallery.model.ToolbarType
import com.numero.material_gallery.view.ToolbarTypeAdapter
import kotlinx.android.synthetic.main.activity_toolbar_type.*

class ToolbarTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_type)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        toolbarTypeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ToolbarTypeActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@ToolbarTypeActivity, DividerItemDecoration.VERTICAL))
            adapter = ToolbarTypeAdapter().apply {
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

    private fun selectedToolbarType(toolbarType: ToolbarType) {
        val intent = when (toolbarType) {
            ToolbarType.ACTION_BAR -> TODO()
            ToolbarType.LIFT_ON_SCROLL -> TODO()
            ToolbarType.COLLAPSING -> CollapsingActivity.createIntent(this)
        }
        startActivity(intent)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ToolbarTypeActivity::class.java)
    }
}