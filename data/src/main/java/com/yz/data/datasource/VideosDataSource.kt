package com.yz.data.datasource

import com.yz.domain.model.Video

interface VideosDataSource {

    suspend fun getVideos(): Result<List<Video>>

}