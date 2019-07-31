package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ThemeInfoBottomSheetDialog
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_text_field.*
import org.koin.android.ext.android.inject
import android.widget.ArrayAdapter

class TextFieldActivity : AppCompatActivity(R.layout.activity_text_field) {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.themeRes)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        filledErrorTextInputLayout.error = "Error"
        outlineErrorTextInputLayout.error = "Error"

        customIconFilledTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(this, "Clicked end icon", Toast.LENGTH_SHORT).show()
        }
        customIconOutlineTextInputLayout.setEndIconOnClickListener {
            Toast.makeText(this, "Clicked end icon", Toast.LENGTH_SHORT).show()
        }

        val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        )
        filledAutoCompleteTextView.setAdapter(adapter)
        outlineAutoCompleteTextView.setAdapter(adapter)
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

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TextFieldActivity::class.java)
    }
}