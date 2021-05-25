package com.numero.material_gallery.components.menu

import android.annotation.SuppressLint
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.databinding.FragmentMenuBinding
import dev.chrisbanes.insetter.applyInsetter

class MenuFragment : MaterialContainerTransformFragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)

        binding.scrollView.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }

        binding.popupMenuButton.setOnClickListener {
            showPopupMenu(it)
        }
        binding.iconPopupMenuButton.setOnClickListener {
            showPopupMenuWithIcon(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_common, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_current_theme -> {
                findNavController().navigate(R.id.action_show_ThemeInfoDialog)
                true
            }
            else -> false
        }
    }

    private fun showPopupMenu(targetView: View) {
        val popup = PopupMenu(targetView.context, targetView)
        popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)
        popup.show()
    }

    @SuppressLint("RestrictedApi")
    private fun showPopupMenuWithIcon(targetView: View) {
        val popup = PopupMenu(targetView.context, targetView)
        popup.menuInflater.inflate(R.menu.menu_icon_popup, popup.menu)
        (popup.menu as? MenuBuilder)?.let { menuBuilder ->
            menuBuilder.setOptionalIconsVisible(true)
            val iconMargin = 8f
            for (item in menuBuilder.visibleItems) {
                val iconMarginPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    iconMargin,
                    resources.displayMetrics
                ).toInt()
                if (item.icon != null) {
                    item.icon = InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx, 0)
                }
            }
        }
        popup.show()
    }
}