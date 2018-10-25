package com.numero.material_gallery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.model.ToolbarType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_component.*

class ToolbarTypeAdapter : RecyclerView.Adapter<ToolbarTypeAdapter.ComponentViewHolder>() {

    private var listener: ((toolbarType: ToolbarType) -> Unit)? = null

    fun setOnItemClickListener(listener: ((toolbarType: ToolbarType) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_component, parent, false)
        return ComponentViewHolder(view)
    }

    override fun getItemCount(): Int = ToolbarType.values().size

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        val component = ToolbarType.values()[position]
        holder.setComponent(component)
        holder.itemView.setOnClickListener {
            listener?.invoke(component)
        }
    }

    class ComponentViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setComponent(toolbarType: ToolbarType) {
            nameTextView.setText(toolbarType.titleRes)
        }
    }
}