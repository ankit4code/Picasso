
package com.eb.picasso.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eb.picasso.commons.models.Picture
import com.eb.picasso.databinding.PictureListBinding

class PictureAdapter : ListAdapter<Picture, RecyclerView.ViewHolder>(PictureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PictureViewHolder(PictureListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val picture = getItem(position)
        (holder as PictureViewHolder).bind(picture)
    }

    class PictureViewHolder(
        private val binding: PictureListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.picture?.let { picture ->
                    navigateToPlant(picture, it)
                }
            }
        }

        private fun navigateToPlant(
            plant: Picture,
            view: View
        ) {
            /*val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                    plant.plantId
                )
            view.findNavController().navigate(direction)*/
        }

        fun bind(item: Picture) {
            binding.apply {
                picture = item
                executePendingBindings()
            }
        }
    }
}

private class PictureDiffCallback : DiffUtil.ItemCallback<Picture>() {

    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}