package com.numero.material_gallery.components.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.components.DesignComponent
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_item.*

class ComponentListAdapter : RecyclerView.Adapter<ComponentListAdapter.ComponentItemViewHolder>() {

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

    class ComponentItemViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(component: DesignComponent) {
            titleTextView.setText(component.titleRes)
        }
    }
}