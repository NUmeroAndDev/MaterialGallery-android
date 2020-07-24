package com.numero.material_gallery

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.numero.material_gallery.core.observeSingle
import com.numero.material_gallery.repository.ConfigRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

        configRepository.changedTheme.observeSingle(this) {
            recreate()
        }
    }
}
