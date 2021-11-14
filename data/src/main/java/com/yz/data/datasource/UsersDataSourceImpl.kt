package com.yz.data.datasource

import com.yz.common.extensions.lazyCoroutine
import com.yz.domain.model.User
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val mockDataDataSource: MockDataDataSource
) : UsersDataSource {

    private val cachedItems = coroutineScope.lazyCoroutine {
        val jsonItemsResult = mockDataDataSource.getJsonItems()
        mapJsonItemsToUsers(jsonItemsResult)
    }

    // TODO Wrong ids in json file?
    override suspend fun getUserById(userId: String): Result<User> {
        return cachedItems.await().map {
            it.first { it.id.equals(userId) }
        }
    }

    override suspend fun getUserByName(userName: String): Result<User> {
        return cachedItems.await().map {
            it.first { it.name.equals(userName) }
        }
    }

    override suspend fun getUsers(): Result<List<User>> {
        return cachedItems.await()
    }

    private fun mapJsonItemsToUsers(jsonItemsResult: Result<List<JsonItem>>) =
        jsonItemsResult.mapCatching { it.map { it.mapToUser() } }

}