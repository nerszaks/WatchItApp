package com.yz.data.datasource

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AssetsDataSource {

    suspend fun readTextFile(fileName: String): Result<String>

}