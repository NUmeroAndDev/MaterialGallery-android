package com.numero.material_gallery.components.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_card.*

class SelectionCardAdapter : RecyclerView.Adapter<SelectionCardAdapter.CardViewHolder>() {

    private val itemList: List<SelectionCardItem>

    init {
        itemList = (0..10).map { SelectionCardItem(R.drawable.ic_android, "Card $it") }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = itemList[position]
        holder.setSelectionCardItem(item)
        holder.setOnClickCardListener {
            item.isSelected = item.isSelected.not()
            notifyItemChanged(position)
        }
    }

    class CardViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun setSelectionCardItem(item: SelectionCardItem) {
            cardView.isChecked = item.isSelected
            iconImageView.setImageResource(item.icon)
            titleTextView.text = item.title
        }

        fun setOnClickCardListener(listener: (view: View) -> Unit) {
            cardView.setOnClickListener {
                listener(it)
            }
        }
    }

    data class SelectionCardItem(
            @DrawableRes val icon: Int,
            val title: String
    ) {
        var isSelected = false
    }
}