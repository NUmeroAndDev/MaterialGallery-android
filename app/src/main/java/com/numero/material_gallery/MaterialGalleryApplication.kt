package com.numero.material_gallery

import android.app.Application
import com.numero.material_gallery.repository.ConfigRepository
import com.numero.material_gallery.repository.IConfigRepository
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

class MaterialGalleryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

    private val appModule = module {
        single { ConfigRepository(androidContext()) as IConfigRepository }
    }
}