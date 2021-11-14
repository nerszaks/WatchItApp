package com.yz.presentation.screen.videoslist

import com.yz.domain.model.Post

data class VideosListState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String = ""
)