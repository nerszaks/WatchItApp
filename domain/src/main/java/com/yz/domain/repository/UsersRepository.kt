package com.yz.domain.repository

import com.yz.domain.model.User

interface UsersRepository {

    suspend fun getUserById(id: String): Result<User>

    suspend fun getUserByName(userName: String): Result<User>

    suspend fun getUsers(): Result<List<User>>

}