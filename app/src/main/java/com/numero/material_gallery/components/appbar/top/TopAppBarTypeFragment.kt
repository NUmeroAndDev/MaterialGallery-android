package com.numero.material_gallery.components.appbar.top

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.numero.material_gallery.R
import com.numero.material_gallery.components.MaterialContainerTransformFragment
import com.numero.material_gallery.repository.ConfigRepository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_top_app_bar_type.*
import kotlinx.android.synthetic.main.view_holder_item.*
import org.koin.android.ext.android.inject

class TopAppBarTypeFragment : MaterialContainerTransformFragment() {

    private val configRepository by inject<ConfigRepository>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater
                .from(ContextThemeWrapper(context, configRepository.themeRes))
                .inflate(R.layout.fragment_top_app_bar_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        toolbarTypeRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = TopAppBarTypeItemAdapter().apply {
                setOnItemClickListener { _, type ->
                    selectedToolbarType(type)
                }
            }
        }
    }

    private fun selectedToolbarType(topAppBarType: TopAppBarType) {
        val intent = when (topAppBarType) {
            TopAppBarType.ACTION_BAR -> ActionBarActivity.createIntent(requireContext())
            TopAppBarType.TOOLBAR -> ToolbarActivity.createIntent(requireContext())
            TopAppBarType.LIFT_ON_SCROLL -> LiftOnScrollActivity.createIntent(requireContext())
            TopAppBarType.COLLAPSING -> CollapsingActivity.createIntent(requireContext())
        }
        startActivity(intent)
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
        }

        private val TopAppBarType.titleRes: Int
            @StringRes
            get() = when (this) {
                TopAppBarType.ACTION_BAR -> R.string.actionbar
                TopAppBarType.TOOLBAR -> R.string.toolbar
                TopAppBarType.LIFT_ON_SCROLL -> R.string.lift_on_scroll
                TopAppBarType.COLLAPSING -> R.string.collapsing
            }
    }
}