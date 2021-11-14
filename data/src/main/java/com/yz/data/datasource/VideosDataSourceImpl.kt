package com.yz.data.datasource

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.yz.domain.model.Video
import javax.inject.Inject

class VideosDataSourceImpl @Inject constructor(
    private val mockDataDataSource: MockDataDataSource
) : VideosDataSource {

    override suspend fun getVideos(): Result<List<Video>> {
        val jsonItemsResult = mockDataDataSource.getJsonItems()
        return mapJsonItemsToVideos(jsonItemsResult)
    }

    private fun mapJsonItemsToVideos(jsonItemsResult: Result<List<JsonItem>>) =
        jsonItemsResult.mapCatching { it.map { it.mapToVideo() } }

}