package com.yz.data.datasource

import android.content.Context
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AssetsDataSource {

    @WorkerThread
    suspend fun readTextFile(fileName: String): Result<String>

}