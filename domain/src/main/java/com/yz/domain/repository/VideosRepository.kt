package com.yz.domain.repository

import com.yz.domain.model.Video

interface VideosRepository {

    suspend fun getVideos(): Result<List<Video>>

}