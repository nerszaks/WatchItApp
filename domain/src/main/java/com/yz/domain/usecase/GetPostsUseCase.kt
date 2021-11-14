package com.yz.domain.usecase

import com.yz.domain.model.Post
import com.yz.domain.repository.UsersRepository
import com.yz.domain.repository.VideosRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val videosRepository: VideosRepository,
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(): Result<List<Post>> {
        val videos = videosRepository.getVideos()
        val postsResult = videos.mapCatching { videos ->
            videos.map { video ->
                val user = usersRepository.getUserByName(video.userName).getOrThrow()
                Post(video, user)
            }
        }
        return postsResult
    }

}