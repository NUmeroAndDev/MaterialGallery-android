package com.numero.material_gallery.core

import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

val Context.isDarkTheme: Boolean
    get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun <T> Flow<T>.launchWhenStartedIn(lifecycleOwner: LifecycleOwner): Job =
    launchWhenStartedIn(lifecycleOwner.lifecycleScope)

fun <T> Flow<T>.launchWhenStartedIn(lifecycleScope: LifecycleCoroutineScope): Job =
    lifecycleScope.launchWhenStarted {
        collect()
    }