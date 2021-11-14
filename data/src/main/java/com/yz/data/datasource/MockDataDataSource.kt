package com.yz.data.datasource

interface MockDataDataSource {

    suspend fun getJsonItems(): Result<List<JsonItem>>

}