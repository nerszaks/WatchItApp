package com.yz.presentation.screen.videoslist

import com.yz.domain.model.Video

data class VideosListState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val error: String = ""
)