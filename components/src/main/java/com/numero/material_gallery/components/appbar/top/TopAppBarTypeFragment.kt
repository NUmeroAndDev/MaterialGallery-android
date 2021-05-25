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
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.FragmentTopAppBarTypeBinding
import com.numero.material_gallery.components.databinding.ViewHolderItemBinding
import com.numero.material_gallery.core.MaterialContainerTransformFragment

class TopAppBarTypeFragment : MaterialContainerTransformFragment(R.layout.fragment_top_app_bar_type) {

    private var _binding: FragmentTopAppBarTypeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopAppBarTypeBinding.bind(view)

        binding.toolbarTypeRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = TopAppBarTypeItemAdapter().apply {
                setOnItemClickListener { view, type ->
                    selectedToolbarType(view, type)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectedToolbarType(view: View, topAppBarType: TopAppBarType) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        when (topAppBarType) {
            TopAppBarType.ACTION_BAR -> {
                findNavController().navigate(
                    R.id.action_TopAppBarType_to_ActionBar,
                    null,
                    null,
                    extras
                )
            }
            TopAppBarType.TOOLBAR -> {
                findNavController().navigate(
                    R.id.action_TopAppBarType_to_Toolbar,
                    null,
                    null,
                    extras
                )
            }
            TopAppBarType.LIFT_ON_SCROLL -> {
                startActivity(LiftOnScrollActivity.createIntent(requireContext()))
            }
            TopAppBarType.COLLAPSING -> {
                findNavController().navigate(
                    R.id.action_TopAppBarType_to_Collapsing,
                    null,
                    null,
                    extras
                )
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
        val view = ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(
        private val binding: ViewHolderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindType(type: TopAppBarType) {
            binding.titleTextView.setText(type.titleRes)
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