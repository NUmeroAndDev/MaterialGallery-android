package com.numero.material_gallery

import android.app.Application
import com.numero.material_gallery.repository.ConfigRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MaterialGalleryApplication : Application() {

    @Inject
    lateinit var configRepository: ConfigRepository

    override fun onCreate() {
        super.onCreate()
        configRepository.getCurrentTheme().apply()
    }
}