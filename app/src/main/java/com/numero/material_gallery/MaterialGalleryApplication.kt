package com.numero.material_gallery

import android.app.Application
import com.numero.material_gallery.repository.ConfigRepository
import com.numero.material_gallery.repository.IConfigRepository
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
        val configRepository: IConfigRepository = get()
        configRepository.getCurrentTheme().apply()
    }

    private val appModule = module {
        single { ConfigRepository(androidContext()) as IConfigRepository }
    }
}