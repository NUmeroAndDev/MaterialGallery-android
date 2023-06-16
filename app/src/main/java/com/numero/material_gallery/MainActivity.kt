package com.numero.material_gallery

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.color.DynamicColors
import com.google.android.material.navigation.NavigationBarView
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import com.numero.material_gallery.core.ShapeTheme
import com.numero.material_gallery.core.isDarkTheme
import com.numero.material_gallery.core.launchWhenStartedIn
import com.numero.material_gallery.core.repository.ConfigRepository
import com.numero.material_gallery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var configRepository: ConfigRepository

    @Inject
    lateinit var appUpdateManager: AppUpdateManager

    private val hideAppBarDestinationIds = setOf(
        R.id.NavigationDrawerScreen,
        R.id.SearchBarScreen,
        R.id.CollapsingScreen,
        R.id.ComponentListScreen,
        R.id.StudiesScreen,
        R.id.SettingsScreen,

        R.id.Material2Screen,
        R.id.Material3Screen,
        R.id.CraneScreen,
        R.id.ReplyScreen,
        R.id.ShrineScreen
    )

    private val rootNavigationDestinationIds = setOf(
        R.id.ComponentListScreen,
        R.id.StudiesScreen,
        R.id.SettingsScreen
    )

    private val darkStatusBarDestinationIds = setOf(
        R.id.Material2Screen,
        R.id.CraneScreen,
        R.id.ReplyScreen,
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(configRepository.currentShapeTheme.themeRes)
        if (DynamicColors.isDynamicColorAvailable()) {
            DynamicColors.applyToActivityIfAvailable(this)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        binding.appbar.applyInsetter {
            type(
                statusBars = true,
            ) {
                padding(
                    top = true
                )
            }
        }

        val navController = binding.container.getFragment<NavHostFragment>().navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.requireNavigationView.setupWithNavController(navController)
        binding.requireNavigationView.setOnItemReselectedListener { }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isHideAppBar = hideAppBarDestinationIds.contains(destination.id)
            binding.appbar.visibility(!isHideAppBar)

            val isRootDestination = rootNavigationDestinationIds.contains(destination.id)
            binding.requireNavigationView.isVisible = isRootDestination
            updateInsets(!isRootDestination)

            val isDarkStatusBarDestination = darkStatusBarDestinationIds.contains(destination.id)
            val windowInsetController = WindowInsetsControllerCompat(window, window.decorView)
            windowInsetController.isAppearanceLightStatusBars =
                !isDarkStatusBarDestination && !isDarkTheme
        }

        configRepository.changedThemeEvent.onEach {
            recreate()
        }.launchWhenStartedIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        checkUpdate()
    }

    override fun onSupportNavigateUp() = checkNotNull(
        supportFragmentManager.findFragmentById(R.id.container)
    ).findNavController().navigateUp()

    private fun updateInsets(isApplyInsets: Boolean) {
        binding.rootLayout.applyInsetter {
            if (isApplyInsets) {
                type(
                    displayCutout = true,
                    statusBars = true,
                    navigationBars = true,
                    captionBar = true
                ) {
                    padding(horizontal = true)
                }
            } else {
                binding.rootLayout.updatePadding(left = 0, right = 0)
            }
        }
    }

    private fun checkUpdate() {
        appUpdateManager.requestUpdateFlow()
            .onEach { appUpdate ->
                when (appUpdate) {
                    is AppUpdateResult.Available -> {
                        binding.requireNavigationView.getOrCreateBadge(R.id.navSettings)
                    }
                    else -> {
                        binding.requireNavigationView.removeBadge(R.id.navSettings)
                    }
                }
            }
            .catch {}
            .launchWhenStartedIn(lifecycleScope)
    }

    private fun AppBarLayout.visibility(isVisibility: Boolean) {
        updateLayoutParams<ViewGroup.LayoutParams> {
            height = if (isVisibility) ViewGroup.LayoutParams.WRAP_CONTENT else 0
        }
    }

    private val ShapeTheme.themeRes: Int
        get() {
            return when (this) {
                ShapeTheme.ROUNDED -> R.style.Theme_MaterialGallery_DayNight_Rounded
                ShapeTheme.CUT -> R.style.Theme_MaterialGallery_DayNight_Cut
            }
        }

    private val ActivityMainBinding.requireNavigationView: NavigationBarView
        get() = navigation as NavigationBarView
}
