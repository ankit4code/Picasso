package com.eb.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eb.picasso.R
import com.eb.picasso.commons.models.Picture
import com.eb.picasso.databinding.PictureListBinding
import kotlinx.android.synthetic.main.activity_main.*


class PicAdapter(private var list:List<Picture>) : RecyclerView.Adapter<PicAdapter.PicViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder{
        return PicViewHolder(
            PictureListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        val plant = list[position]
        holder.bind(plant)
    }

    override fun getItemCount() = list.size

    public fun refresh(list:List<Picture>){
        this.list=list;
        notifyDataSetChanged()
    }

    class PicViewHolder(private val binding:PictureListBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.setClickListener {
                binding.picture?.let { picture ->
                    navigateToPlant(picture, it)
                }
            }
        }

        private fun navigateToPlant(movie: Picture, view: View) {

            view.findNavController().navigate(R.id.action_home2_to_detailActivity)
            /*val directions = HomeDirections.actionHome2ToMovieDetail(movie.id)

            view.findNavController().navigate(directions)*/
        }

        fun bind(item: Picture) {
            binding.apply {
                picture = item
                executePendingBindings()
            }
        }
    }


}