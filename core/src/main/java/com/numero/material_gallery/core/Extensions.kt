package com.numero.material_gallery.core

import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.util.concurrent.atomic.AtomicBoolean

fun <T> singleLiveData(): MutableLiveData<T> {
    return MutableLiveData<T>().apply {
        value = null
    }
}

fun <T> LiveData<T>.observeSingle(owner: LifecycleOwner, observer: ((T) -> Unit)) {
    val firstIgnore = AtomicBoolean(true)
    this.observe(owner, Observer {
        if (firstIgnore.getAndSet(false)) return@Observer
        observer(it)
    })
}

val Context.isDarkTheme: Boolean
    get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun <T> Flow<T>.launchWhenStartedIn(lifecycleOwner: LifecycleOwner): Job =
    launchWhenStartedIn(lifecycleOwner.lifecycleScope)

fun <T> Flow<T>.launchWhenStartedIn(lifecycleScope: LifecycleCoroutineScope): Job =
    lifecycleScope.launchWhenStarted {
        collect()
    }