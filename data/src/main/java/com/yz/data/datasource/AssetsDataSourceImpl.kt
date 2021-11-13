package com.yz.data.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AssetsDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AssetsDataSource {

    override suspend fun readTextFile(fileName: String): Result<String> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val text = context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
                return@withContext Result.success(text)
            }
        }
    }

}