package com.numero.material_gallery.core

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

fun View.applySystemWindowInsetsPadding(
        applyLeft: Boolean = false,
        applyTop: Boolean = false,
        applyRight: Boolean = false,
        applyBottom: Boolean = false
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insetsCompat ->
        val leftInset = if (applyLeft) insetsCompat.systemWindowInsetLeft else 0
        val topInset = if (applyTop) insetsCompat.systemWindowInsetTop else 0
        val rightInset = if (applyRight) insetsCompat.systemWindowInsetRight else 0
        val bottomInset = if (applyBottom) insetsCompat.systemWindowInsetBottom else 0
        view.updatePadding(
                left = leftInset,
                top = topInset,
                right = rightInset,
                bottom = bottomInset
        )
        insetsCompat
    }
}