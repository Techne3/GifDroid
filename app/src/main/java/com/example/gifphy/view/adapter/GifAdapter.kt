package com.example.gifphy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifphy.R
import com.example.gifphy.databinding.ItemLayoutBinding
import com.example.gifphy.networks.models.GifModel

class GifAdapter : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {


    private val usersList = mutableListOf<GifModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun submitList(image: List<GifModel>) {
        usersList.clear()
        usersList.addAll(image)
        notifyDataSetChanged()
    }

    class GifViewHolder(
        private val binding: ItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: GifModel) = with(binding) {

           val urls = "${ gif.data.images }.png"

            Glide.with(gifIv.context)
                .load(urls)
                .placeholder(R.drawable.ic_launcher_background)
                .into(gifIv)

        }
    }
}