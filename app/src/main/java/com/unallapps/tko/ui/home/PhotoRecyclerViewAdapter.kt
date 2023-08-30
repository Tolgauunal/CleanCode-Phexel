package com.unallapps.tko.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.unallapps.tko.data.api.model.Photo
import com.unallapps.tko.databinding.CustomItemViewBinding

class PhotoRecyclerViewAdapter(val photoList: List<Photo>) :
    RecyclerView.Adapter<PhotoRecyclerViewAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            CustomItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val photo = photoList[position]
        holder.binding.customImge.load(photo.src.portrait)
        holder.binding.photographerName.text = photo.photographer
    }

    class CustomViewHolder(val binding: CustomItemViewBinding) : ViewHolder(binding.root) {
    }
}