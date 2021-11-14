package com.yz.presentation.screen.videoslist.mediplayer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.yz.presentation.databinding.VideoListItemBinding
import com.yz.presentation.screen.videoslist.ViewHolder

private const val TAG = "VideosPlaybackManager"

// TODO
class VideosPlaybackManager: LifecycleObserver {

    var playerManager: ExoplayerManager? = null

    private var currentViewHolder: ViewHolder? = null
    private var currentPosition: Int? = null
    private var currentUrl: String? = null

    private val lastPlaybackPositions = hashMapOf<String, Long>()

    fun release() {
        Log.d(TAG, "release")
        pauseCurrent()

        currentViewHolder = null
        currentPosition = null
        currentUrl = null

        playerManager?.release()
    }

    fun onActive(viewHolder: ViewHolder, position: Int, url: String, useIfIdleOnly: Boolean) {
        Log.d(TAG, "onActive $url $position $useIfIdleOnly")
        if ((currentPosition != position && !useIfIdleOnly) || (useIfIdleOnly && currentPosition == null)) {
            pauseCurrent()
            start(viewHolder, url, position)
        }
    }

    private fun start(
        viewHolder: ViewHolder,
        url: String,
        position: Int
    ) {
        Log.d(TAG, "start $url $position")

        val binding = VideoListItemBinding.bind(viewHolder.itemView)
        playerManager?.play(
            binding.videoView,
            url,
            lastPlaybackPositions.get(url) ?: 0
        )

        currentViewHolder = viewHolder
        currentPosition = position
        currentUrl = url
    }

    fun onNonActive(position: Int) {
        if (currentPosition == position) {
            pauseCurrent()
        }
    }

    private fun pauseCurrent() {
        Log.d(TAG, "pauseCurrent $currentPosition $currentUrl")
        playerManager?.pause()
        currentViewHolder?.let {
            val binding = VideoListItemBinding.bind(it.itemView)
            binding.videoView.player = null
        }
        savePositionForCurrentUrl()
    }

    private fun savePositionForCurrentUrl() {
        currentUrl?.let {
            lastPlaybackPositions.put(it, playerManager?.getCurrentPosition() ?: 0)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeRequired() {
        playerManager?.resume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseRequired() {
        pauseCurrent()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun releaseVideos() {
        release()
    }

}