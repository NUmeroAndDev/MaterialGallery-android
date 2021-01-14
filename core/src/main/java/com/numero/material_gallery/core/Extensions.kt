package com.numero.material_gallery.core

import android.content.Context
import android.content.res.Configuration
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
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

fun View.applySystemWindowInsetsPadding(
    applyLeft: Boolean = false,
    applyTop: Boolean = false,
    applyRight: Boolean = false,
    applyBottom: Boolean = false
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insetsCompat ->
        val sysWindow = insetsCompat.getInsets(
            WindowInsetsCompat.Type.navigationBars() or WindowInsetsCompat.Type.statusBars()
        )
        val leftInset = if (applyLeft) sysWindow.left else 0
        val topInset = if (applyTop) sysWindow.top else 0
        val rightInset = if (applyRight) sysWindow.right else 0
        val bottomInset = if (applyBottom) sysWindow.bottom else 0
        view.updatePadding(
            left = leftInset,
            top = topInset,
            right = rightInset,
            bottom = bottomInset
        )
        insetsCompat
    }
}

fun <T> Flow<T>.launchWhenStartedIn(lifecycleOwner: LifecycleOwner): Job =
    launchWhenStartedIn(lifecycleOwner.lifecycleScope)

fun <T> Flow<T>.launchWhenStartedIn(lifecycleScope: LifecycleCoroutineScope): Job =
    lifecycleScope.launchWhenStarted {
        collect()
    }