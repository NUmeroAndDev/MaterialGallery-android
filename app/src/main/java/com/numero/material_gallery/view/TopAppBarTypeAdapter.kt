package com.numero.material_gallery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.model.TopAppBarType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_component.*

class TopAppBarTypeAdapter : RecyclerView.Adapter<TopAppBarTypeAdapter.ComponentViewHolder>() {

    private var listener: ((topAppBarType: TopAppBarType) -> Unit)? = null

    fun setOnItemClickListener(listener: ((topAppBarType: TopAppBarType) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_component, parent, false)
        return ComponentViewHolder(view)
    }

    override fun getItemCount(): Int = TopAppBarType.values().size

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        val component = TopAppBarType.values()[position]
        holder.setComponent(component)
        holder.itemView.setOnClickListener {
            listener?.invoke(component)
        }
    }

    class ComponentViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setComponent(topAppBarType: TopAppBarType) {
            nameTextView.setText(topAppBarType.titleRes)
        }
    }
}