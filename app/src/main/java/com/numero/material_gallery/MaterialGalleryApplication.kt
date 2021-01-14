package com.numero.material_gallery

import android.app.Application
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.numero.material_gallery.repository.ConfigRepository
import com.numero.material_gallery.repository.ConfigRepositoryImpl
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
        single<ConfigRepository> { ConfigRepositoryImpl(androidContext()) }
        single { AppUpdateManagerFactory.create(androidContext()) }
    }
}