package com.yz.presentation.screen.videoslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yz.domain.model.Video
import com.yz.presentation.databinding.VideoListItemBinding
import javax.inject.Inject

class VideosListAdapter @Inject constructor() :
    ListAdapter<Video, ViewHolder>(VideoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            VideoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ViewHolder(private val binding: VideoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(video: Video) {
        binding.description.setText(video.videoDescription)
    }
}

class VideoItemDiffCallback : DiffUtil.ItemCallback<Video>() {

    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.equals(newItem)
    }
}