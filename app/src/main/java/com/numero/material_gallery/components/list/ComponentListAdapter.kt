package com.numero.material_gallery.components.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.components.DesignComponent
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_item.*

class ComponentListAdapter : RecyclerView.Adapter<ComponentItemViewHolder>() {

    private val componentList = DesignComponent.values().toList()

    private var listener: ((view: View, component: DesignComponent) -> Unit)? = null

    fun setOnItemClickListener(listener: ((view: View, component: DesignComponent) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item, parent, false)
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
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(component: DesignComponent) {
        titleTextView.setText(component.titleRes)
        itemView.transitionName = itemView.context.getString(component.transitionNameStringRes)
    }

    private val DesignComponent.transitionNameStringRes: Int
        @StringRes
        get() = when (this) {
            DesignComponent.BOTTOM_NAVIGATION -> R.string.bottom_navigation_transition_name
            DesignComponent.BOTTOM_APP_BAR -> R.string.bottom_app_bar_transition_name
            DesignComponent.BOTTOM_SHEET -> R.string.bottom_sheet_transition_name
            DesignComponent.CHECKBOX -> R.string.checkbox_transition_name
            DesignComponent.CHIPS -> R.string.chips_transition_name
            DesignComponent.DATE_PICKER -> R.string.date_picker_transition_name
            DesignComponent.EXTENDED_FAB -> R.string.extended_fab_transition_name
            DesignComponent.FAB -> R.string.fab_transition_name
            DesignComponent.IMAGE_VIEW -> R.string.shapeable_image_view_transition_name
            DesignComponent.MATERIAL_BUTTON -> R.string.material_button_transition_name
            DesignComponent.MATERIAL_BUTTON_TOGGLE_GROUP -> R.string.material_button_toggle_group_transition_name
            DesignComponent.MATERIAL_CARD -> R.string.material_card_transition_name
            DesignComponent.MATERIAL_DIALOG -> R.string.material_dialog_transition_name
            DesignComponent.MODAL_BOTTOM_SHEET -> R.string.modal_bottom_sheet_transition_name
            DesignComponent.NAVIGATION_DRAWER -> R.string.navigation_drawer_transition_name
            DesignComponent.PROGRESS_INDICATOR -> R.string.progress_indicator_transition_name
            DesignComponent.RADIO_BUTTON -> R.string.radio_button_transition_name
            DesignComponent.SLIDER -> R.string.slider_transition_name
            DesignComponent.SNACKBAR -> R.string.snackbar_transition_name
            DesignComponent.SWITCH -> R.string.material_switch_transition_name
            DesignComponent.TAB -> R.string.tab_transition_name
            DesignComponent.TEXT_FIELDS -> R.string.text_field_transition_name
            DesignComponent.TIME_PICKER -> R.string.time_picker_transition_name
            DesignComponent.TOP_APP_BAR -> R.string.top_app_bar_transition_name
        }

}