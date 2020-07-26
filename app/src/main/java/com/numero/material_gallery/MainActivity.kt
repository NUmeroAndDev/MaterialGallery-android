package com.numero.material_gallery

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.numero.material_gallery.core.observeSingle
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val configRepository by inject<ConfigRepository>()

    private val hideAppBarDestinationIds = setOf(
            R.id.NavigationDrawerScreen,
            R.id.CollapsingScreen
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

        val navController = checkNotNull(
                supportFragmentManager.findFragmentById(R.id.container)
        ).findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isHideAppBar = hideAppBarDestinationIds.contains(destination.id)
            if (destination.id != R.id.ThemeInfoDialog) {
                if (isHideAppBar) {
                    appbar.gone()
                } else {
                    appbar.visible()
                }
            }
        }

        configRepository.changedTheme.observeSingle(this) {
            recreate()
        }
    }

    override fun onSupportNavigateUp() = checkNotNull(
            supportFragmentManager.findFragmentById(R.id.container)
    ).findNavController().navigateUp()

    private fun AppBarLayout.gone() {
        updateLayoutParams<CoordinatorLayout.LayoutParams> {
            height = 0
        }
    }

    private fun AppBarLayout.visible() {
        updateLayoutParams<CoordinatorLayout.LayoutParams> {
            height = CoordinatorLayout.LayoutParams.WRAP_CONTENT
        }
    }
}
