package com.numero.material_gallery

import android.app.Application
import com.numero.material_gallery.repository.ConfigRepositoryImpl
import com.numero.material_gallery.repository.ConfigRepository
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MaterialGalleryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MaterialGalleryApplication)
            modules(appModule)
        }
        val configRepository: ConfigRepository = get()
        configRepository.getCurrentTheme().apply()
    }

    private val appModule = module {
        single { ConfigRepositoryImpl(androidContext()) as ConfigRepository }
    }
}