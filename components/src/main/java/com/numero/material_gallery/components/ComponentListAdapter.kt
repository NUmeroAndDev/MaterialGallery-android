package com.numero.material_gallery.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.components.databinding.ViewHolderItemBinding

class ComponentListAdapter : RecyclerView.Adapter<ComponentItemViewHolder>() {

    private val componentList = MaterialComponent.values().toList()

    private var listener: ((view: View, component: MaterialComponent) -> Unit)? = null

    fun setOnItemClickListener(listener: ((view: View, component: MaterialComponent) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentItemViewHolder {
        val view = ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComponentItemViewHolder(view)
    }

    override fun getItemCount(): Int = componentList.size

    override fun onBindViewHolder(holder: ComponentItemViewHolder, position: Int) {
        val component = componentList[position]
        holder.bind(component)
        holder.itemView.setOnClickListener {
            listener?.invoke(it, component)
        }
    }
}

class ComponentItemViewHolder(
    private val binding: ViewHolderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(component: MaterialComponent) {
        binding.titleTextView.setText(component.titleRes)
        itemView.transitionName = itemView.context.getString(component.transitionNameStringRes)
    }

    private val MaterialComponent.transitionNameStringRes: Int
        @StringRes
        get() = when (this) {
            MaterialComponent.BADGE -> R.string.badge_transition_name
            MaterialComponent.NAVIGATION_BAR -> R.string.bottom_navigation_transition_name
            MaterialComponent.BOTTOM_APP_BAR -> R.string.bottom_app_bar_transition_name
            MaterialComponent.BOTTOM_SHEET -> R.string.bottom_sheet_transition_name
            MaterialComponent.Carousel -> R.string.carousel_transition_name
            MaterialComponent.CHECKBOX -> R.string.checkbox_transition_name
            MaterialComponent.CHIPS -> R.string.chips_transition_name
            MaterialComponent.DATE_PICKER -> R.string.date_picker_transition_name
            MaterialComponent.DIVIDER -> R.string.divider_transition_name
            MaterialComponent.EXTENDED_FAB -> R.string.extended_fab_transition_name
            MaterialComponent.FAB -> R.string.fab_transition_name
            MaterialComponent.IMAGE_VIEW -> R.string.shapeable_image_view_transition_name
            MaterialComponent.MATERIAL_BUTTON -> R.string.material_button_transition_name
            MaterialComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> R.string.material_button_toggle_group_transition_name
            MaterialComponent.MATERIAL_CARD -> R.string.material_card_transition_name
            MaterialComponent.MATERIAL_DIALOG -> R.string.material_dialog_transition_name
            MaterialComponent.MENU -> R.string.menu_transition_name
            MaterialComponent.MODAL_BOTTOM_SHEET -> R.string.modal_bottom_sheet_transition_name
            MaterialComponent.NAVIGATION_DRAWER -> R.string.navigation_drawer_transition_name
            MaterialComponent.NAVIGATION_RAIL -> R.string.navigation_rail_transition_name
            MaterialComponent.PROGRESS_INDICATOR -> R.string.progress_indicator_transition_name
            MaterialComponent.RADIO_BUTTON -> R.string.radio_button_transition_name
            MaterialComponent.SIDE_SHEET -> R.string.side_sheet_transition_name
            MaterialComponent.SLIDER -> R.string.slider_transition_name
            MaterialComponent.SNACKBAR -> R.string.snackbar_transition_name
            MaterialComponent.SWITCH -> R.string.material_switch_transition_name
            MaterialComponent.TAB -> R.string.tab_transition_name
            MaterialComponent.TEXT_FIELDS -> R.string.text_field_transition_name
            MaterialComponent.TIME_PICKER -> R.string.time_picker_transition_name
            MaterialComponent.TOP_APP_BAR -> R.string.top_app_bar_transition_name
        }

}