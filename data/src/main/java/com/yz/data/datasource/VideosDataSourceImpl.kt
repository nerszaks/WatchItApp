package com.yz.data.datasource

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.yz.domain.model.Video
import javax.inject.Inject

private const val ASSETS_FILE_NAME = "videos.json"

class VideosDataSourceImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource
) : VideosDataSource {

    override suspend fun getVideos(): Result<List<Video>> {
        val readTextFile = assetsDataSource.readTextFile(ASSETS_FILE_NAME)
        val jsonVideosResult = parseJsonVideos(readTextFile)
        return mapJsonVideosToVideos(jsonVideosResult)
    }

    private fun mapJsonVideosToVideos(jsonVideosResult: Result<List<JsonVideo>>) =
        jsonVideosResult.mapCatching { it.map { it.mapToVideo() } }

    private fun parseJsonVideos(readTextFile: Result<String>): Result<List<JsonVideo>> {
        val videosFromJsonResult = readTextFile.mapCatching {
            val gson = Gson()
            val itemType = object : TypeToken<List<JsonVideo>>() {}.type
            val videosFromJsonList = gson.fromJson<List<JsonVideo>>(it, itemType)
            videosFromJsonList
        }
        return videosFromJsonResult
    }
}

private class JsonVideo(
    @SerializedName("video_description") private val videoDescription: String,
    @SerializedName("video_path") private val videoPath: String
) {

    fun mapToVideo(): Video {
        return Video(
            videoDescription,
            videoPath
        )
    }

}