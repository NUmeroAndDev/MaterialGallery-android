package com.numero.material_gallery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.model.DesignComponent
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_component.*

class ComponentAdapter : RecyclerView.Adapter<ComponentAdapter.ComponentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_component, parent, false)
        return ComponentViewHolder(view)
    }

    override fun getItemCount(): Int = DesignComponent.values().size

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        val component = DesignComponent.values()[position]
        holder.setComponent(component)
    }

    class ComponentViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setComponent(component: DesignComponent) {
            nameTextView.isEnabled = component.isEnable
            nameTextView.setText(component.nameRes)
        }
    }
}