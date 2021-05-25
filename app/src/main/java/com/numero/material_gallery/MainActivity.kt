package com.numero.material_gallery

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import com.numero.material_gallery.core.isDarkTheme
import com.numero.material_gallery.core.launchWhenStartedIn
import com.numero.material_gallery.core.observeSingle
import com.numero.material_gallery.databinding.ActivityMainBinding
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val configRepository by inject<ConfigRepository>()
    private val appUpdateManager by inject<AppUpdateManager>()

    private val hideAppBarDestinationIds = setOf(
        R.id.NavigationDrawerScreen,
        R.id.CollapsingScreen,
        R.id.ComponentListScreen,
        R.id.StudiesScreen,
        R.id.SettingsScreen,

        R.id.CraneScreen,
        R.id.ReplyScreen,
        R.id.ShrineScreen
    )

    private val rootNavigationDestinationIds = setOf(
        R.id.ComponentListScreen,
        R.id.StudiesScreen,
        R.id.SettingsScreen
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

        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemReselectedListener { }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isHideAppBar = hideAppBarDestinationIds.contains(destination.id)
            if (destination.id != R.id.ThemeInfoDialog) {
                if (isHideAppBar) {
                    binding.appbar.gone()
                } else {
                    binding.appbar.visible()
                }
            }

            val isRootDestination = rootNavigationDestinationIds.contains(destination.id)
            binding.bottomNavigation.isVisible = isRootDestination

            val windowInsetController = WindowInsetsControllerCompat(window, window.decorView)
            windowInsetController.isAppearanceLightStatusBars = isRootDestination && !isDarkTheme
        }

        configRepository.changedTheme.observeSingle(this) {
            recreate()
        }
    }

    override fun onResume() {
        super.onResume()
        checkUpdate()
    }

    override fun onSupportNavigateUp() = checkNotNull(
        supportFragmentManager.findFragmentById(R.id.container)
    ).findNavController().navigateUp()

    private fun checkUpdate() {
        appUpdateManager.requestUpdateFlow()
            .onEach { appUpdate ->
                when (appUpdate) {
                    is AppUpdateResult.Available -> {
                        binding.bottomNavigation.getOrCreateBadge(R.id.navSettings)
                    }
                    else -> {
                        binding.bottomNavigation.removeBadge(R.id.navSettings)
                    }
                }
            }
            .launchWhenStartedIn(lifecycleScope)
    }

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
