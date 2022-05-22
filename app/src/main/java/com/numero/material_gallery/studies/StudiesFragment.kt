package com.numero.material_gallery.studies

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.numero.material_gallery.R
import com.numero.material_gallery.core.delegate.viewBinding
import com.numero.material_gallery.databinding.FragmentStudiesBinding
import dev.chrisbanes.insetter.applyInsetter

class StudiesFragment : Fragment(R.layout.fragment_studies) {

    private val binding by viewBinding { FragmentStudiesBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        ViewGroupCompat.setTransitionGroup(binding.rootLayout, true)

        exitTransition = MaterialFadeThrough()
        enterTransition = MaterialFadeThrough()

        initViews()
        binding.studiesRecyclerView.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
    }

    private fun initViews() {
        binding.studiesRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = MaterialStudiesAdapter().apply {
                setOnItemClickListener { view, component ->
                    selectedStudies(view, component)
                }
            }
        }
    }

    private fun selectedStudies(view: View, studies: MaterialStudies) {
        exitTransition = Hold()
        reenterTransition = null

        val extras = FragmentNavigatorExtras(view to view.transitionName)
        findNavController().navigate(studies.navigationId, null, null, extras)
    }

    private val MaterialStudies.navigationId: Int
        get() = when (this) {
            MaterialStudies.Material2 -> R.id.action_Studies_to_Material2
            MaterialStudies.Material3 -> R.id.action_Studies_to_Material3
            MaterialStudies.Crane -> R.id.action_Studies_to_Crane
            MaterialStudies.Reply -> R.id.action_Studies_to_Reply
            MaterialStudies.Shrine -> R.id.action_Studies_to_Shrine
        }
}
