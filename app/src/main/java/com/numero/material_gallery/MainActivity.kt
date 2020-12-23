package com.numero.material_gallery

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.numero.material_gallery.core.observeSingle
import com.numero.material_gallery.databinding.ActivityMainBinding
import com.numero.material_gallery.repository.ConfigRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val configRepository by inject<ConfigRepository>()

    private val hideAppBarDestinationIds = setOf(
            R.id.NavigationDrawerScreen,
            R.id.CollapsingScreen
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(configRepository.currentTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        WindowCompat.setDecorFitsSystemWindows(window, false)
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
                    binding.appbar.gone()
                } else {
                    binding.appbar.visible()
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
