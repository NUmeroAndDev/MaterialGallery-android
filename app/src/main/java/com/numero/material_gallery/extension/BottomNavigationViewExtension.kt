package com.numero.material_gallery.extension

import androidx.annotation.IdRes
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setVisibleItem(index: Int, isVisible: Boolean) {
    menu.getItem(index).isVisible = isVisible
}

fun BottomNavigationView.setCheckedItem(@IdRes idRes: Int, isChecked: Boolean) {
    menu.findItem(idRes).isChecked = isChecked
}

val BottomNavigationView.visibleItemCount: Int
    get() {
        var count = 0
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            if (menuItem.isVisible) {
                count++
            }
        }
        return count
    }