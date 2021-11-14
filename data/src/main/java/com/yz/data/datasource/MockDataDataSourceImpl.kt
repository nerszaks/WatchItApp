package com.yz.data.datasource

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.yz.common.extensions.lazyCoroutine
import com.yz.domain.model.User
import com.yz.domain.model.Video
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

private const val ASSETS_FILE_NAME = "videos.json"

class MockDataDataSourceImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val assetsDataSource: AssetsDataSource
) : MockDataDataSource {

    private val cachedItems = coroutineScope.lazyCoroutine {
        val readTextFile = assetsDataSource.readTextFile(ASSETS_FILE_NAME)
        parseJsonVideos(readTextFile)
    }

    override suspend fun getJsonItems(): Result<List<JsonItem>> {
        return cachedItems.await()
    }

    private fun parseJsonVideos(readTextFile: Result<String>): Result<List<JsonItem>> {
        val videosFromJsonResult = readTextFile.mapCatching {
            val gson = Gson()
            val itemType = object : TypeToken<List<JsonItem>>() {}.type
            val videosFromJsonList = gson.fromJson<List<JsonItem>>(it, itemType)
            videosFromJsonList
        }
        return videosFromJsonResult
    }

}

class JsonItem(
    @SerializedName("video_description") private val videoDescription: String,
    @SerializedName("video_path") private val videoPath: String,
    @SerializedName("video_number_likes") private val videoNumberLikes: Int,
    @SerializedName("video_number_comments") private val videoNumberComments: Int,
    @SerializedName("user_id") private val userId: String,
    @SerializedName("user_name") private val userName: String,
    @SerializedName("user_image_path") private val userImagePath: String,
) {

    fun mapToVideo(): Video {
        return Video(
            userId,
            userName,
            videoDescription,
            videoPath,
            videoNumberLikes,
            videoNumberComments
        )
    }

    fun mapToUser(): User {
        return User(
            userId,
            userName,
            userImagePath
        )
    }

}