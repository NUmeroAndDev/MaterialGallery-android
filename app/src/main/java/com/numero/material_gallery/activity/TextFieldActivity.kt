package com.numero.material_gallery.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.R
import com.numero.material_gallery.fragment.ColorInfoBottomSheetDialog
import com.numero.material_gallery.repository.IConfigRepository
import kotlinx.android.synthetic.main.activity_text_field.*
import org.koin.android.ext.android.inject

class TextFieldActivity : AppCompatActivity(R.layout.activity_text_field) {

    private val configRepository by inject<IConfigRepository>()

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
        fun createIntent(context: Context): Intent = Intent(context, TextFieldActivity::class.java)
    }
}