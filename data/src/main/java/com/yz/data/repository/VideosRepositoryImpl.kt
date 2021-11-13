package com.yz.data.repository

import com.yz.data.datasource.AssetsDataSource
import com.yz.data.datasource.VideosDataSource
import com.yz.domain.model.Video
import com.yz.domain.repository.VideosRepository
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor(
    private val videosDataSource: VideosDataSource
) : VideosRepository {

    override suspend fun getVideos(): Result<List<Video>> {
        return videosDataSource.getVideos()
    }

}