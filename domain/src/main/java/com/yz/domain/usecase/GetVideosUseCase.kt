package com.yz.domain.usecase

import com.yz.domain.model.Video
import com.yz.domain.repository.VideosRepository
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val videosRepository: VideosRepository
) {

    suspend operator fun invoke(): Result<List<Video>> {
        return videosRepository.getVideos()
    }

}