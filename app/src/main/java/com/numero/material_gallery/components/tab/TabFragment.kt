package com.numero.material_gallery.components.tab

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.synthetic.main.fragment_tab.*
import org.koin.android.ext.android.inject

class TabFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            inflateMenu(R.menu.menu_common)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_current_theme -> {
                        findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                        true
                    }
                    else -> false
                }
            }
        }
        setupTab()
    }

    private fun setupTab() {
        withBadgeTabLayout.getTabAt(0)?.apply {
            orCreateBadge
        }
        withBadgeTabLayout.getTabAt(1)?.apply {
            orCreateBadge.apply {
                number = 10
            }
        }
        withBadgeTabLayout.getTabAt(2)?.apply {
            orCreateBadge.apply {
                number = 1000
            }
        }
        withBadgeTabLayout.getTabAt(3)?.apply {
            orCreateBadge.apply {
                maxCharacterCount = 5
                number = 5000
            }
        }
    }
}