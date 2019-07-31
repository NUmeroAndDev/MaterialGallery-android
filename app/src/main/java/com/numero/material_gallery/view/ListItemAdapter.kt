package com.numero.material_gallery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.model.ListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_item.*

class ListItemAdapter<T : ListItem>(
        private val itemList: List<T>
) : RecyclerView.Adapter<ListItemAdapter.ItemViewHolder<T>>() {

    private var listener: ((item: T) -> Unit)? = null

    fun setOnItemClickListener(listener: ((item: T) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int) {
        val item = itemList[position]
        holder.setItem(item)
        holder.itemView.setOnClickListener {
            listener?.invoke(item)
        }
    }

    class ItemViewHolder<T : ListItem>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setItem(item: T) {
            titleTextView.setText(item.titleRes)
        }
    }
}