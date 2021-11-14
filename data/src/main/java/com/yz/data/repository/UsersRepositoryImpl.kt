package com.yz.data.repository

import com.yz.data.datasource.UsersDataSource
import com.yz.domain.model.User
import com.yz.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersDataSource: UsersDataSource
) : UsersRepository {

    override suspend fun getUserById(userId: String): Result<User> {
        return usersDataSource.getUserById(userId)
    }

    override suspend fun getUserByName(userName: String): Result<User> {
        return usersDataSource.getUserByName(userName)
    }


    override suspend fun getUsers(): Result<List<User>> {
        return usersDataSource.getUsers()
    }

}