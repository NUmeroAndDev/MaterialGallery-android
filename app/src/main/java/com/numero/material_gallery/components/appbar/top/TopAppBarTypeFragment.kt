package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_top_app_bar_type.*
import kotlinx.android.synthetic.main.view_holder_item.*

class TopAppBarTypeFragment : MaterialContainerTransformFragment(R.layout.fragment_top_app_bar_type) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTypeRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = TopAppBarTypeItemAdapter().apply {
                setOnItemClickListener { view, type ->
                    selectedToolbarType(view, type)
                }
            }
        }
    }

    private fun selectedToolbarType(view: View, topAppBarType: TopAppBarType) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        when (topAppBarType) {
            TopAppBarType.ACTION_BAR -> {
                findNavController().navigate(R.id.action_TopAppBarType_to_ActionBar, null, null, extras)
            }
            TopAppBarType.TOOLBAR -> {
                findNavController().navigate(R.id.action_TopAppBarType_to_Toolbar, null, null, extras)
            }
            TopAppBarType.LIFT_ON_SCROLL -> {
                startActivity(LiftOnScrollActivity.createIntent(requireContext()))
            }
            TopAppBarType.COLLAPSING -> {
                startActivity(CollapsingActivity.createIntent(requireContext()))
            }
        }
    }
}

enum class TopAppBarType {
    ACTION_BAR,
    TOOLBAR,
    LIFT_ON_SCROLL,
    COLLAPSING
}

class TopAppBarTypeItemAdapter : RecyclerView.Adapter<TopAppBarTypeItemAdapter.ViewHolder>() {

    private val typeList = TopAppBarType.values().toList()

    private var clickListener: ((view: View, type: TopAppBarType) -> Unit)? = null

    fun setOnItemClickListener(listener: ((view: View, type: TopAppBarType) -> Unit)) {
        this.clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = typeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = typeList[position]
        holder.bindType(type)
        holder.itemView.setOnClickListener {
            clickListener?.invoke(it, type)
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindType(type: TopAppBarType) {
            titleTextView.setText(type.titleRes)
            itemView.transitionName = itemView.context.getString(type.transitionNameStringRes)
        }

        private val TopAppBarType.titleRes: Int
            @StringRes
            get() = when (this) {
                TopAppBarType.ACTION_BAR -> R.string.actionbar
                TopAppBarType.TOOLBAR -> R.string.toolbar
                TopAppBarType.LIFT_ON_SCROLL -> R.string.lift_on_scroll
                TopAppBarType.COLLAPSING -> R.string.collapsing
            }

        private val TopAppBarType.transitionNameStringRes: Int
            @StringRes
            get() = when (this) {
                TopAppBarType.ACTION_BAR -> R.string.actionbar_transition_name
                TopAppBarType.TOOLBAR -> R.string.toolbar_transition_name
                TopAppBarType.LIFT_ON_SCROLL -> R.string.lift_on_scroll_transition_name
                TopAppBarType.COLLAPSING -> R.string.collapsing_transition_name
            }
    }
}