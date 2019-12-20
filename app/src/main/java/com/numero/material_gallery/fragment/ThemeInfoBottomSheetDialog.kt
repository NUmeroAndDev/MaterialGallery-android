package com.numero.material_gallery.fragment

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.R
import com.numero.material_gallery.model.Theme
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.bottom_sheet_fragment_theme_info.view.*
import org.koin.android.ext.android.inject

class ThemeInfoBottomSheetDialog : BottomSheetDialogFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun setupDialog(dialog: Dialog, style: Int) {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_fragment_theme_info, null)
        setupViews(view)
        dialog.setContentView(view)
    }

    private fun setupViews(view: View) {
        val toggleGroup = view.selectThemeToggleGroup
        toggleGroup.check(configRepository.getCurrentTheme().getToggleButtonId())
        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                val theme = when (checkedId) {
                    R.id.lightThemeButton -> Theme.LIGHT
                    R.id.darkThemeButton -> Theme.DARK
                    R.id.systemThemeButton -> Theme.SYSTEM_DEFAULT
                    else -> throw IllegalArgumentException()
                }
                configRepository.updateAndApplyTheme(theme)
            }
        }
    }

    fun showIfNeed(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) != null) {
            return
        }
        show(fragmentManager, TAG)
    }

    private fun Theme.getToggleButtonId(): Int {
        return when (this) {
            Theme.LIGHT -> R.id.lightThemeButton
            Theme.DARK -> R.id.darkThemeButton
            Theme.SYSTEM_DEFAULT -> R.id.systemThemeButton
        }
    }

    companion object {
        private const val TAG = "ThemeInfoBottomSheetDialog"

        fun newInstance(): ThemeInfoBottomSheetDialog = ThemeInfoBottomSheetDialog()
    }
}