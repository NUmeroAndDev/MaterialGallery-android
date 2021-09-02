package com.numero.material_gallery.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.numero.material_gallery.core.databinding.BottomSheetFragmentThemeInfoBinding
import com.numero.material_gallery.core.repository.ConfigRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ThemeInfoBottomSheetDialog : BottomSheetDialogFragment() {

    @Inject
    lateinit var configRepository: ConfigRepository

    private var _binding: BottomSheetFragmentThemeInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetFragmentThemeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showIfNeeded(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(Tag) == null) {
            show(fragmentManager, Tag)
        }
    }

    private fun setupViews() {
        val toggleGroup = binding.selectThemeToggleGroup
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

    private fun Theme.getToggleButtonId(): Int {
        return when (this) {
            Theme.LIGHT -> R.id.lightThemeButton
            Theme.DARK -> R.id.darkThemeButton
            Theme.SYSTEM_DEFAULT -> R.id.systemThemeButton
        }
    }

    companion object {
        private const val Tag = "ThemeInfoBottomSheetDialog"
    }
}