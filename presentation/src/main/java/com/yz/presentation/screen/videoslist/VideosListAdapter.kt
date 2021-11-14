package com.yz.presentation.screen.videoslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yz.domain.model.Post
import com.yz.presentation.databinding.VideoListItemBinding
import com.yz.presentation.listener.OnItemClickListener
import com.yz.presentation.screen.videoslist.mediplayer.VideosPlaybackManager

// TODO
class VideosListAdapter(val playbackManager: VideosPlaybackManager) :
    ListAdapter<Post, ViewHolder>(PostItemDiffCallback()) {

    private var onItemClickListener: OnItemClickListener<Post>? = null

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
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(getItem(position))
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<Post>) {
        this.onItemClickListener = onItemClickListener
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            handleCurrentPosition(recyclerView)
        }
    }

    private fun handleCurrentPosition(recyclerView: RecyclerView) {
        val findFirstVisibleItemPosition =
            (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()

        findFirstVisibleItemPosition?.let { position ->
            (recyclerView.findViewHolderForAdapterPosition(position) as? ViewHolder)?.let { viewHolder ->
                passActiveViewHolder(viewHolder, position)
            }
        } ?: run {
            playbackManager.release()
        }
    }

    private fun passActiveViewHolder(
        viewHolder: ViewHolder,
        position: Int,
        useIfIdle: Boolean = false
    ) {
        playbackManager.onActive(
            viewHolder,
            position,
            getItem(position).video.videoPath,
            useIfIdle
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        recyclerView.removeOnScrollListener(scrollListener)
        playbackManager.release()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val adapterPosition = holder.adapterPosition
        passActiveViewHolder(holder, adapterPosition, true)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        playbackManager.onNonActive(holder.adapterPosition)
    }

}

class ViewHolder(private val binding: VideoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.videoView.useController = false
        binding.description.setText(post.video.videoDescription)
    }
}

class PostItemDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.equals(newItem)
    }
}