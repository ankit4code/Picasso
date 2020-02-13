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
import com.eb.picasso.databinding.SearchPictureListBinding
import com.eb.picasso.databinding.SliderPictureListBinding
import kotlinx.android.synthetic.main.activity_main.*


class SearchAdapter(private var list:List<Picture>) : RecyclerView.Adapter<SearchAdapter.PicViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder{
        return PicViewHolder(
            SearchPictureListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class PicViewHolder(private val binding:SearchPictureListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Picture) {
            binding.apply {
                picture = item
                executePendingBindings()
            }
        }
    }


}