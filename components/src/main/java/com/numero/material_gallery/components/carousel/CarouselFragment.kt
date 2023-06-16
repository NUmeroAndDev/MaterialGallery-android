package com.numero.material_gallery.components.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.numero.material_gallery.components.ComponentFragment
import com.numero.material_gallery.components.R
import com.numero.material_gallery.components.databinding.CarouselItemBinding
import com.numero.material_gallery.components.databinding.FragmentCarouselBinding
import com.numero.material_gallery.core.delegate.viewBinding
import dev.chrisbanes.insetter.applyInsetter

class CarouselFragment : ComponentFragment(R.layout.fragment_carousel) {

    private val binding by viewBinding { FragmentCarouselBinding.bind(it) }

    private val adapter = CarouselListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.applyInsetter {
            type(navigationBars = true) {
                padding()
            }
        }
        binding.recyclerView.layoutManager = CarouselLayoutManager()
        binding.recyclerView.adapter = adapter
    }
}

class CarouselListAdapter : RecyclerView.Adapter<CarouselItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val view = CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselItemViewHolder(view)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
    }
}

class CarouselItemViewHolder(
    binding: CarouselItemBinding
) : RecyclerView.ViewHolder(binding.root)