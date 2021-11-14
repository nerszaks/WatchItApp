package com.yz.domain.model

data class Video(
    // TODO - Can't use it as we have the same id for different user names
    val userId: String,
    val userName: String,
    val videoDescription: String,
    val videoPath: String,
    val numberLikes: Int,
    val numberComments: Int
)