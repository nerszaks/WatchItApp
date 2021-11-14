package com.yz.data.datasource

import com.yz.domain.model.User

interface UsersDataSource {

    suspend fun getUserById(userId: String): Result<User>

    suspend fun getUserByName(userName: String): Result<User>

    suspend fun getUsers(): Result<List<User>>

}