package com.numero.material_gallery.studies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.databinding.ViewHolderItemBinding

class MaterialStudiesAdapter : RecyclerView.Adapter<StudiesItemViewHolder>() {

    private val studies = MaterialStudies.values().toList()

    private var listener: ((view: View, studies: MaterialStudies) -> Unit)? = null

    fun setOnItemClickListener(listener: ((view: View, studies: MaterialStudies) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiesItemViewHolder {
        val view = ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudiesItemViewHolder(view)
    }

    override fun getItemCount(): Int = studies.size

    override fun onBindViewHolder(holder: StudiesItemViewHolder, position: Int) {
        val component = studies[position]
        holder.bind(component)
        holder.itemView.setOnClickListener {
            listener?.invoke(it, component)
        }
    }
}

class StudiesItemViewHolder(
    private val binding: ViewHolderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(studies: MaterialStudies) {
        binding.titleTextView.setText(studies.titleRes)
        binding.iconImageView.isVisible = true
        binding.iconImageView.setImageResource(studies.iconRes)
        itemView.transitionName = itemView.context.getString(studies.transitionNameStringRes)
    }

    private val MaterialStudies.transitionNameStringRes: Int
        @StringRes
        get() = when (this) {
            MaterialStudies.Crane -> R.string.crane_transition_name
            MaterialStudies.Reply -> R.string.reply_transition_name
            MaterialStudies.Shrine -> R.string.shrine_transition_name
        }
}